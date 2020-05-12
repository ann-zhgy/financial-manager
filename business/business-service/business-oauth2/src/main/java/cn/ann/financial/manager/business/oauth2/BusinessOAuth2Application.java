package cn.ann.financial.manager.business.oauth2;

import cn.ann.financial.manager.config.ApplicationListenerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:2:06
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackageClasses = {
        ApplicationListenerConfiguration.class,
        BusinessOAuth2Application.class,
})
public class BusinessOAuth2Application {
    public static void main(String[] args) {
        SpringApplication.run(BusinessOAuth2Application.class, args);
    }
}

