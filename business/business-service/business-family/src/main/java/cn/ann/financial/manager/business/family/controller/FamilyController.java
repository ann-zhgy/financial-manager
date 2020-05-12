package cn.ann.financial.manager.business.family.controller;

import cn.ann.financial.manager.business.family.dto.MemberDTO;
import cn.ann.financial.manager.business.family.dto.TbFamilyDTO;
import cn.ann.financial.manager.business.family.dto.param.ModifyFamily;
import cn.ann.financial.manager.commons.constant.Identity;
import cn.ann.financial.manager.commons.constant.ProviderConstant;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import cn.ann.financial.manager.provider.family.api.TbFamilyServiceApi;
import cn.ann.financial.manager.provider.family.domain.TbFamily;
import cn.ann.financial.manager.provider.user.api.TbUserRoleServiceApi;
import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import cn.ann.financial.manager.provider.user.domain.TbRole;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import cn.ann.financial.manager.provider.user.domain.TbUserRole;
import cn.ann.financial.manager.provider.user.param.UpdateFamilyParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-4-20:10:07
 */
@RestController
@RequestMapping("family")
public class FamilyController {
    @Reference(version = "1.0.0")
    private TbFamilyServiceApi familyService;

    @Reference(version = "1.0.0")
    private TbUserServiceApi userService;

    @Reference(version = "1.0.0")
    private TbUserRoleServiceApi userRoleService;

    /**
     * 获取家庭信息
     *
     * @return {@link ResponseResult<TbFamilyDTO>}
     */
    @GetMapping
    public ResponseResult<TbFamilyDTO> getFamily() {
        TbUser tbUser = this.getUser();
        if (tbUser.getFamilyId() != null) {
            TbFamily tbFamily = familyService.get(tbUser.getFamilyId());
            TbFamilyDTO dto = new TbFamilyDTO();
            BeanUtils.copyProperties(tbFamily, dto);
            dto.setIdentity(this.getUserIdentity(tbUser.getId()));
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取家庭信息成功", dto);
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "该用户还没有关联家庭信息");
    }

    /**
     * 通过用户名获取家庭信息
     *
     * @param username 用户名
     * @return {@link ResponseResult<TbFamilyDTO>}
     */
    @GetMapping("{username}")
    public ResponseResult<TbFamilyDTO> getFamily(@PathVariable String username) {
        TbUser tbUser = userService.get(username);
        if (tbUser.getFamilyId() != null) {
            TbFamily tbFamily = familyService.get(tbUser.getFamilyId());
            TbFamilyDTO dto = new TbFamilyDTO();
            BeanUtils.copyProperties(tbFamily, dto);
            dto.setIdentity(this.getUserIdentity(tbUser.getId()));
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取家庭信息成功", dto);
        }

        return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "该用户还没有关联家庭信息");
    }

    /**
     * 获取家庭成员信息
     *
     * @return {@link ResponseResult<TbFamilyDTO>}
     */
    @GetMapping("members")
    public ResponseResult<List<MemberDTO>> getMembers() {
        TbUser tbUser = this.getUser();
        List<TbUser> users = userService.getListByFamilyId(tbUser.getFamilyId());
        List<MemberDTO> dto = users.stream().map(user -> {
            MemberDTO member = new MemberDTO();
            BeanUtils.copyProperties(user, member);
            member.setName(StringUtils.isNotBlank(user.getNickName()) ? user.getNickName() : user.getUsername());
            Identity identity = this.getUserIdentity(user.getId());
            member.setIdentity(identity);
            return member;
        }).collect(Collectors.toList());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取家庭成员信息成功", dto);
    }

    /**
     * 创建家庭
     *
     * @param family {@link TbFamily}
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping("save")
    public ResponseResult<Void> createFamily(@RequestBody TbFamily family) {
        TbUser user = this.getUser();
        // 创建 family
        family.setUserCount(1);
        family.setUserId(user.getId());
        InsertResult<TbFamily> insertResult = familyService.insert(family);

        boolean isUpdate = this.updateRoles(user, insertResult.getT().getId());

        if (insertResult.getModifyCount() > 0 && isUpdate) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "创建家庭成功");
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "创建家庭失败");
        }
    }

    /**
     * 更新家庭信息
     *
     * @param param {@link ModifyFamily}
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping("modify")
    public ResponseResult<Void> updateFamily(@RequestBody ModifyFamily param) {
        TbFamily family = new TbFamily();
        BeanUtils.copyProperties(param, family);
        int result = familyService.update(family);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新家庭成功");
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "更新家庭失败");
        }
    }

    /**
     * 退出家庭
     *
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping("exit")
    public ResponseResult<Void> exitFamily() {
        TbUser user = this.getUser();
        boolean isUpdate = this.updateRoles(user, null);
        if (isUpdate) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "退出家庭成功");
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "退出家庭失败");
        }
    }

    /**
     * 踢出家庭
     *
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping("exit/{userId}")
    public ResponseResult<Void> exitFamily(@PathVariable Long userId) {
        TbUser user = userService.get(userId);
        Long id = this.getUserId();
        Identity loginUserIdentity = this.getUserIdentity(id);
        Identity identity = this.getUserIdentity(userId);
        if (id.equals(userId)
                || identity.getId().equals(ProviderConstant.ROLE_CREATOR)
                || identity.getId().equals(loginUserIdentity.getId())) {
            return new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "踢出家庭失败");
        } else {
            boolean isUpdate = this.updateRoles(user, null);
            if (isUpdate) {
                return new ResponseResult<>(ResponseResult.CodeStatus.OK, "踢出家庭成功");
            } else {
                return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "踢出家庭失败");
            }
        }
    }

    /**
     * 删除家庭
     *
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping("delete")
    public ResponseResult<Void> deleteFamily() {
        TbUser user = this.getUser();
        Long familyId = user.getFamilyId();
        if (familyId != null) {
            List<TbUser> users = userService.getListByFamilyId(familyId);
            users.forEach(tbUser -> {
                this.updateRoles(tbUser, null);
            });

            // 删除 family
            int result1 = familyService.delete(familyId);

            if (result1 > 0) {
                return new ResponseResult<>(ResponseResult.CodeStatus.OK, "删除家庭成功");
            } else {
                return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "删除家庭失败");
            }
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "您没有创建家庭");
        }
    }

    /**
     * 设置管理员
     *
     * @param userId 设置管理员的 用户 id
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping("admin/{userId}")
    public ResponseResult<Void> setAdmin(@PathVariable Long userId) {
        StringBuilder message = new StringBuilder();
        TbUser user = userService.get(userId);
        String name = StringUtils.isBlank(user.getNickName()) ? user.getUsername() : user.getNickName();
        Identity identity = this.getUserIdentity(userId);
        if (identity.getId().equals(ProviderConstant.ROLE_ADMIN)) {
            message.append("撤销").append(name).append("的管理员身份");
        } else {
            message.append("设置").append(name).append("为管理员");
        }
        boolean isUpdate = this.updateRoles(user, user.getFamilyId());
        if (isUpdate) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, message.append("成功").toString());
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, message.append("失败").toString());
        }
    }

    /**
     * 加入家庭
     *
     * @param familyId 家庭 id
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping("join")
    public ResponseResult<Void> join(@RequestBody Long familyId) {
        boolean isUpdate = this.updateRoles(this.getUser(), familyId);
        if (isUpdate) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "加入家庭成功");
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "加入家庭失败");
        }
    }

    /**
     * 更新角色信息
     *
     * @param user     要更新的用户
     * @param familyId 将要变成什么 familyId
     */
    private boolean updateRoles(TbUser user, Long familyId) {
        AtomicInteger flag = new AtomicInteger();
        // 如果当前角色的 familyId 和 传入的 familyId 不相等，就更新用户与 familyId 的关联关系
        if (!Objects.equals(user.getFamilyId(), familyId)) {
            // 更新 user
            UpdateFamilyParam param = new UpdateFamilyParam();
            param.setId(user.getId());
            param.setFamilyId(familyId);
            userService.updateFamily(param);
        }
        // 当前操作为 删除家庭、退出家庭或踢出家庭
        List<TbRole> roles = userService.getRoles(user.getId());
        if (familyId == null) {
            // 更新 角色
            TbUserRole userRole = new TbUserRole();
            userRole.setUserId(user.getId());
            roles.forEach(tbRole -> {
                if (tbRole.getId().equals(ProviderConstant.ROLE_FAMILY_PUBLIC)) {
                    // 删除家庭共有角色权限
                    flag.addAndGet(userRoleService.delete(user.getId(), tbRole.getId()));
                } else if (!tbRole.getId().equals(ProviderConstant.ROLE_PUBLIC)) {
                    // 变更当前权限为 用户
                    userRole.setRoleId(tbRole.getId());
                    Long userRoleId = userRoleService.get(userRole.getUserId(), userRole.getRoleId());
                    userRole.setId(userRoleId);
                    userRole.setRoleId(ProviderConstant.ROLE_USER);
                    flag.addAndGet(userRoleService.update(userRole));
                }
            });
        }
        // 当前操作为 创建家庭 或 设置管理员 或 加入家庭
        else {
            // 更新 角色
            TbFamily family = familyService.get(familyId);
            TbUserRole userRole = new TbUserRole();
            userRole.setUserId(user.getId());
            roles.forEach(tbRole -> {
                // 原来角色为用户，当前操作为创建家庭或加入家庭
                if (tbRole.getId().equals(ProviderConstant.ROLE_USER)) {
                    userRole.setRoleId(tbRole.getId());
                    Long userRoleId = userRoleService.get(userRole.getUserId(), userRole.getRoleId());
                    userRole.setId(userRoleId);

                    // 如果家庭的 userId 与传入id 一致，就是创建
                    if (family.getUserId().equals(user.getId())) {
                        // 变更当前权限为 创建者
                        userRole.setRoleId(ProviderConstant.ROLE_CREATOR);
                    }
                    // 如果家庭的 userId 与传入 user 的 id 不一致，则为加入家庭操作
                    else {
                        // 变更当前权限为 家庭成员
                        userRole.setRoleId(ProviderConstant.ROLE_MEMBER);
                    }
                    flag.addAndGet(userRoleService.update(userRole));

                    // 添加家庭共有权限
                    userRole.setRoleId(ProviderConstant.ROLE_FAMILY_PUBLIC);
                    flag.addAndGet(userRoleService.insert(userRole));
                }
                // 原来角色为家庭成员，则当前操作为设置管理员
                else if (tbRole.getId().equals(ProviderConstant.ROLE_MEMBER)) {
                    // 变更当前权限为 管理员
                    userRole.setRoleId(tbRole.getId());
                    Long userRoleId = userRoleService.get(userRole.getUserId(), userRole.getRoleId());
                    userRole.setId(userRoleId);
                    userRole.setRoleId(ProviderConstant.ROLE_ADMIN);
                    flag.addAndGet(userRoleService.update(userRole));
                }
                // 原来角色为管理员，则当前操作为撤销管理员
                else if (tbRole.getId().equals(ProviderConstant.ROLE_ADMIN)) {
                    // 变更当前权限为 管理员
                    userRole.setRoleId(tbRole.getId());
                    Long userRoleId = userRoleService.get(userRole.getUserId(), userRole.getRoleId());
                    userRole.setId(userRoleId);
                    userRole.setRoleId(ProviderConstant.ROLE_MEMBER);
                    flag.addAndGet(userRoleService.update(userRole));
                }
            });
        }
        return flag.intValue() != 0;
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

    /**
     * 获取用户在家庭中的角色
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
            }
        }
        throw new RuntimeException("获取用户角色信息失败");
    }
}
