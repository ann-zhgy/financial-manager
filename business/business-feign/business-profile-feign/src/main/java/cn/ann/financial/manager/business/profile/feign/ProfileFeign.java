package cn.ann.financial.manager.business.profile.feign;

import cn.ann.financial.manager.business.profile.dto.param.IconParam;
import cn.ann.financial.manager.business.profile.dto.param.PasswordParam;
import cn.ann.financial.manager.business.profile.dto.param.ProfileParam;
import cn.ann.financial.manager.business.profile.feign.fallback.ProfileFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:14:10
 */
@FeignClient(value = "business-profile", path = "profile", configuration = cn.ann.financial.manager.configuration.FeignRequestConfiguration.class, fallback = ProfileFeignFallback.class)
public interface ProfileFeign {
    /**
     * 获取个人信息
     *
     * @param username {@code String} 用户名
     * @return {@code String} JSON
     */
    @GetMapping(value = "info/{username}")
    String info(@PathVariable String username);

    /**
     * 更新个人信息
     *
     * @param profileParam {@link ProfileParam}
     * @return {@code String} JSON
     */
    @PostMapping(value = "update")
    String update(@RequestBody ProfileParam profileParam);

    /**
     * 修改密码
     *
     * @param passwordParam {@link PasswordParam}
     * @return {@code String} JSON
     */
    @PostMapping(value = "modify/password")
    String modifyPassword(@RequestBody PasswordParam passwordParam);

    /**
     * 修改头像
     *
     * @param iconParam {@link IconParam}
     * @return {@code String} JSON
     */
    @PostMapping(value = "modify/icon")
    String modifyIcon(@RequestBody IconParam iconParam);
}
