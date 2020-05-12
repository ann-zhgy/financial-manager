package cn.ann.financial.manager.configuration.resource.server;

import cn.ann.financial.manager.configuration.resource.server.service.PermissionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.annotation.Resource;

/**
 * Description：通用资源服务器配置
 * <p>
 * Date: 2020-5-4 18:14
 *
 * @author 88475
 * @version v1.0
 */
@Configuration
@EnableResourceServer
public class CommonResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Value("security.oauth2.resource.id")
    private String resourceId;

    @Resource
    private DefaultWebSecurityExpressionHandler expressionHandler;

    @Bean
    public DefaultWebSecurityExpressionHandler getExpressionHandler(ApplicationContext applicationContext) {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
        resources.resourceId(resourceId).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling();
        // 使用 session 的无状态管理策略
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 开启请求头缓存控制
        http.headers().cacheControl();
        // 配置请求
        http.authorizeRequests()
                .anyRequest()
                .access("@permissionService.hasAuthentication(request, authentication)");
    }
}
