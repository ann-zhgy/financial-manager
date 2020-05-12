package cn.ann.financial.manager.business.oauth2.controller;

import cn.ann.financial.manager.business.oauth2.controller.fallback.UserControllerFallback;
import cn.ann.financial.manager.business.oauth2.dto.LoginInfo;
import cn.ann.financial.manager.business.oauth2.dto.param.LoginParameter;
import cn.ann.financial.manager.commons.constant.AuthorizationConstant;
import cn.ann.financial.manager.commons.constant.Identity;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.utils.MapperUtils;
import cn.ann.financial.manager.commons.utils.OkHttpClientUtils;
import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import cn.ann.financial.manager.provider.user.domain.TbRole;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:9:16
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Resource(name = "tokenStore")
    public TokenStore tokenStore;

    @Reference(version = "1.0.0")
    private TbUserServiceApi userService;

    @PostMapping("login")
    @SentinelResource(value = "login", fallback = "loginFallback", fallbackClass = UserControllerFallback.class)
    public ResponseResult<Map<String, String>> login(@RequestBody LoginParameter parameter) throws Exception {
        UserDetails details = userDetailsService.loadUserByUsername(Objects.requireNonNull(parameter.getUsername()));
        if (details == null || !passwordEncoder.matches(parameter.getPassword(), details.getPassword())) {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "用户名或密码错误", null);
        }
        OkHttpClientUtils client = OkHttpClientUtils.getInstance();
        Map<String, String> result = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        data.put("username", parameter.getUsername());
        data.put("password", parameter.getPassword());
        data.put("grant_type", AuthorizationConstant.GRANT_TYPE_PASSWORD);
        data.put("client_id", AuthorizationConstant.CLIENT_ID);
        data.put("client_secret", AuthorizationConstant.CLIENT_SECRET);

        Response response = client.postData(AuthorizationConstant.TOKEN_URL, data);
        String jsonString = Objects.requireNonNull(response.body()).string();
        Map<String, String> jsonMap = MapperUtils.json2map(jsonString, String.class);
        String access_token = jsonMap.get("access_token");
        result.put("token", access_token);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "登陆成功", result);
    }

    /**
     * 获取用户信息
     *
     * @return {@link ResponseResult}
     */
    @GetMapping(value = "info")
    @SentinelResource(value = "info", fallback = "infoFallback", fallbackClass = UserControllerFallback.class)
    public ResponseResult<LoginInfo> info(
            @RequestAttribute(name = AuthorizationConstant.REQUEST_ATTRIBUTE_TOKEN_KEY) String token) {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TbUser user = userService.get(authentication.getName());
        List<TbRole> roles = userService.getRoles(user.getId());
        Identity identity = null;
        for (TbRole role : roles) {
            if (role.getId().equals(Identity.ADMIN.getId())) {
                identity = Identity.ADMIN;
            } else if (role.getId().equals(Identity.CREATOR.getId())) {
                identity = Identity.CREATOR;
            } else if (role.getId().equals(Identity.MEMBER.getId())) {
                identity = Identity.MEMBER;
            } else if (role.getId().equals(Identity.USER.getId())) {
                identity = Identity.USER;
            }
        }
        // 封装并返回结果
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(StringUtils.isNotBlank(user.getNickName())? user.getNickName() : user.getUsername());
        loginInfo.setAvatar(user.getIcon());
        loginInfo.setToken(token);
        loginInfo.setIdentity(identity);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取用户信息成功", loginInfo);
    }

    /**
     * 注销
     *
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "logout")
    @SentinelResource(value = "logout", fallback = "logoutFallback", fallbackClass = UserControllerFallback.class)
    public ResponseResult<Void> logout(
            @RequestAttribute(name = AuthorizationConstant.REQUEST_ATTRIBUTE_TOKEN_KEY) String token) {
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "用户已注销");
    }

}
