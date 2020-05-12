package cn.ann.financial.manager.business.profile;

import cn.ann.financial.manager.commons.utils.RedisUtils;
import cn.ann.financial.manager.config.ApplicationListenerConfiguration;
import cn.ann.financial.manager.configuration.redis.RedisConfiguration;
import cn.ann.financial.manager.configuration.resource.server.CommonResourceServerConfiguration;
import cn.ann.financial.manager.oauth2.feign.OAuth2Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:13:13
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackageClasses = {
        ApplicationListenerConfiguration.class,
        BusinessProfileApplication.class,
        RedisConfiguration.class,
        OAuth2Feign.class,
        RedisUtils.class,
})
@EnableFeignClients(basePackageClasses = {OAuth2Feign.class})
public class BusinessProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessProfileApplication.class, args);
    }
}