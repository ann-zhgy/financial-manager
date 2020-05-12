package cn.ann.financial.manager.business.profile.controller;

import cn.ann.financial.manager.business.profile.controller.fallback.ProfileControllerFallback;
import cn.ann.financial.manager.business.profile.dto.TbPermissionDTO;
import cn.ann.financial.manager.business.profile.dto.TbUserDTO;
import cn.ann.financial.manager.business.profile.dto.param.PasswordParam;
import cn.ann.financial.manager.business.profile.dto.param.ProfileParam;
import cn.ann.financial.manager.commons.constant.AuthorizationConstant;
import cn.ann.financial.manager.commons.constant.Identity;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.utils.RedisUtils;
import cn.ann.financial.manager.oauth2.feign.OAuth2Feign;
import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import cn.ann.financial.manager.provider.user.domain.TbRole;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.util.AssertUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:13:29
 */
@RestController
@RequestMapping("profile")
public class ProfileController {
    @Reference(version = "1.0.0")
    private TbUserServiceApi userService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private OAuth2Feign oAuth2Feign;

    @Resource
    private RedisUtils redisUtils;

    /**
     * 获取个人信息
     *
     * @return {@link ResponseResult}
     */
    @GetMapping
    @SentinelResource(value = "info", fallback = "infoFallback", fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<TbUserDTO> info() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        TbUserDTO dto = (TbUserDTO) redisUtils.get(username + "UserInfo");
        if (dto == null) {
            TbUser user = userService.get(username);
            dto = new TbUserDTO();
            BeanUtils.copyProperties(user, dto);
            redisUtils.set(username + "UserInfo", dto, AuthorizationConstant.TOKEN_TIMEOUT);
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取个人信息", dto);
    }

    /**
     * 获取菜单
     *
     * @return {@link ResponseResult}
     */
    @GetMapping(value = "menu")
    @SentinelResource(value = "menu", fallback = "menuFallback", fallbackClass = ProfileControllerFallback.class)
    @SuppressWarnings("unchecked")
    public ResponseResult<List<TbPermissionDTO>> menu() {
        TbUserDTO data = info().getData();
        AssertUtil.notNull(data, "获取用户信息失败");
        Identity identity = getUserIdentity(data.getId());
        List<TbPermissionDTO> menu = (List<TbPermissionDTO>) redisUtils.get(identity.getEnName() + "Menu");
        if (menu == null || menu.size() == 0) {
            menu = userService.getMenu(data.getId())
                    .stream()
                    .map(tbPermission -> {
                        TbPermissionDTO dto = new TbPermissionDTO();
                        BeanUtils.copyProperties(tbPermission, dto);
                        return dto;
                    }).collect(Collectors.toList());
            redisUtils.set(identity.getEnName() + "Menu", menu);
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取菜单成功", menu);
    }

    /**
     * 更新个人信息
     *
     * @param param {@link ProfileParam}
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "modify")
    @SentinelResource(value = "update", fallback = "updateFallback", fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<Void> update(@RequestBody ProfileParam param) {
        TbUser user = new TbUser();
        BeanUtils.copyProperties(param, user);
        int result = userService.update(user);
        // 成功
        if (result > 0) {
            redisUtils.delete(SecurityContextHolder.getContext().getAuthentication().getName() + "UserInfo");
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新个人信息成功");
        }
        // 失败
        else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "更新个人信息失败");
        }
    }

    /**
     * 修改密码
     *
     * @param passwordParam {@link PasswordParam}
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "password")
    @SentinelResource(value = "modifyPassword", fallback = "modifyPasswordFallback", fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<Void> modifyPassword(@RequestBody PasswordParam passwordParam) {
        TbUser user = this.getUser();
        // 旧密码正确
        if (passwordEncoder.matches(passwordParam.getOldPassword(), user.getPassword())) {
            int result = userService.modifyPassword(user.getUsername(), passwordParam.getNewPassword());
            if (result > 0) {
                redisUtils.delete("userInfo" + user.getUsername());
                oAuth2Feign.logout();
                return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改密码成功");
            }
        }
        // 旧密码错误
        else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "旧密码不匹配，请重试");
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "修改密码失败");
    }

    /**
     * 修改头像
     *
     * @param path 头像路径
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "icon")
    @SentinelResource(value = "modifyIcon", fallback = "modifyIconFallback", fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<Void> modifyIcon(@RequestBody String path) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int result = userService.modifyIcon(name, path);

        // 成功
        if (result > 0) {
            redisUtils.delete(name + "UserInfo");
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新头像成功");
        }
        // 失败
        else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "更新头像失败");
        }
    }

    /**
     * 删除账号
     *
     * @return {@link ResponseResult}
     */
    @GetMapping(value = "delete")
    @SentinelResource(value = "delete", fallback = "deleteFallback", fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<Void> delete() {
        // TODO 删除用户的前置操作
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int result = userService.delete(userService.get(username).getUsername());
        // 成功
        if (result > 0) {
            redisUtils.delete("userInfo" + username);
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "删除账号成功");
        }
        // 失败
        else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "删除账号失败");
        }
    }

    /**
     * 获取用户角色
     *
     * @param id id
     * @return {@link Identity}
     */
    private Identity getUserIdentity(Long id) {
        List<TbRole> roles = userService.getRoles(id);
        for (TbRole role : roles) {
            if (role.getId().equals(Identity.ADMIN.getId())) {
                return Identity.ADMIN;
            } else if (role.getId().equals(Identity.CREATOR.getId())) {
                return Identity.CREATOR;
            } else if (role.getId().equals(Identity.MEMBER.getId())) {
                return Identity.MEMBER;
            } else if (role.getId().equals(Identity.USER.getId())) {
                return Identity.USER;
            }
        }
        throw new RuntimeException("获取用户角色信息失败");
    }


    /**
     * 获取已登陆的 user 的 id
     *
     * @return {@link Long}
     */
    private Long getUserId() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getIdByName(name);
    }

    /**
     * 获取已登陆的 user
     *
     * @return {@link Long}
     */
    private TbUser getUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.get(name);
    }
}
