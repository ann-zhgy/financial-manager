package cn.ann.financial.manager.business.profile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Description：资源服务器配置
 * <p>
 * Date: 2020-5-1 17:11
 *
 * @author 88475
 * @version v1.0
 */
@Configuration
@EnableResourceServer
public class ProfileResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling();
        // 使用 session 的无状态管理策略
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 开启请求头缓存控制
        http.headers().cacheControl();
        // 配置请求
        http.authorizeRequests()
                // 放行登录、注册请求
                .antMatchers("/profile/delete").denyAll()
                .antMatchers("/profile/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("backend-resources-profile");
    }
}
