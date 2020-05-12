package cn.ann.financial.manager.business.register.controller;

import cn.ann.financial.manager.business.register.controller.fallback.RegisterControllerFallback;
import cn.ann.financial.manager.business.register.dto.RegisterUserDTO;
import cn.ann.financial.manager.business.register.dto.param.ActiveParam;
import cn.ann.financial.manager.business.register.dto.param.RegisterParam;
import cn.ann.financial.manager.commons.constant.ProviderConstant;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import cn.ann.financial.manager.provider.user.api.TbUserRoleServiceApi;
import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import cn.ann.financial.manager.provider.user.domain.TbUserRole;
import cn.ann.financial.manager.provider.user.param.UpdateEnableParam;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:23:09
 */
@RestController
public class RegisterController {
    @Reference(version = "1.0.0")
    private TbUserServiceApi userService;

    @Reference(version = "1.0.0")
    private TbUserRoleServiceApi userRoleService;

    /**
     * 用户注册
     *
     * @param param {@link TbUser}
     * @return 成功则返回新注册用户信息
     */
    @PostMapping("register")
    @SentinelResource(value = "register", fallback = "registerFallback", fallbackClass = RegisterControllerFallback.class)
    public ResponseResult<Void> register(@RequestBody RegisterParam param) {
        String message = validateReg(param);
        // 通过验证
        if (message == null) {
            TbUser user = new TbUser();
            BeanUtils.copyProperties(param, user);
            InsertResult<TbUser> insertResult = userService.insert(user);
            TbUserRole userRole = new TbUserRole();
            userRole.setUserId(insertResult.getT().getId());
            userRole.setRoleId(ProviderConstant.ROLE_PUBLIC);
            int result2 = userRoleService.insert(userRole);
            userRole.setRoleId(ProviderConstant.ROLE_USER);
            int result3 = userRoleService.insert(userRole);
//            this.sendRegisterEmail(insertResult.getT());
            if (insertResult.getModifyCount() > 0 && result2 > 0 && result3 > 0) {
                RegisterUserDTO dto = new RegisterUserDTO();
                dto.setId(insertResult.getT().getId());
                dto.setUsername(insertResult.getT().getUsername());
                return new ResponseResult<>(ResponseResult.CodeStatus.OK, "用户注册成功");
            }
        }

        return new ResponseResult<>(HttpStatus.NOT_ACCEPTABLE.value(), message == null ? "用户注册失败" : message);
    }

    /**
     * 用户激活
     *
     * @param param {@link TbUser}
     * @return 成功则返回新注册用户信息
     */
    @Deprecated
    @PostMapping("active")
    @SentinelResource(value = "active", fallback = "activeFallback", fallbackClass = RegisterControllerFallback.class)
    public ResponseResult<Void> active(@RequestBody ActiveParam param) {
        TbUser user = userService.get(param.getId());
        if (user != null) {
            if (user.getCode().equals(param.getCode())) {
                UpdateEnableParam enableParam = new UpdateEnableParam();
                enableParam.setId(user.getId());
                enableParam.setEnable(ProviderConstant.USER_ENABLE);
                int result = userService.updateEnable(enableParam);
                if (result > 0) {
                    return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "用户激活成功");
                }
            } else {
                return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "验证码不正确");
            }
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "用户注册失败");
    }

    /**
     * 验证用户名
     *
     * @param param 用户名
     * @return 验证结果。存在（不可用）返回 false，不存在（可用）返回 true
     */
    @PostMapping("check/username")
    @SentinelResource(value = "checkUsername", fallback = "checkUsernameFallback", fallbackClass = RegisterControllerFallback.class)
    public ResponseResult<Boolean> checkUsername(@RequestBody Map<String, String> param) {
        TbUser user = userService.get(param.get("username"));
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "验证用户名成功", (user == null));
    }

    /**
     * 验证邮箱是否已经注册
     *
     * @param param 邮箱
     * @return 验证结果。已注册（不可用）返回 false，未注册（可用）返回 true
     */
    @PostMapping("check/email")
    @SentinelResource(value = "checkEmail", fallback = "checkEmailFallback", fallbackClass = RegisterControllerFallback.class)
    public ResponseResult<Boolean> checkEmail(@RequestBody Map<String, String> param) {
        TbUser user = userService.getByEmail(param.get("email"));
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "验证邮箱成功", (user == null));
    }

    /**
     * 验证注册信息.
     *
     * @param param {@link RegisterParam}
     * @return 验证结果
     */
    private String validateReg(RegisterParam param) {
        if (param.getUsername() == null) {
            return "用户名不能为空";
        } else if (userService.get(param.getUsername()) != null) {
            return "用户名已存在";
        } else if (param.getPassword() == null) {
            return "密码不能为空";
        } else if (param.getEmail() == null) {
            return "邮箱不能为空";
        } else {
            if (userService.getByEmail(param.getEmail()) != null) {
                return "邮箱已被注册";
            }

        }

        return null;
    }

}
