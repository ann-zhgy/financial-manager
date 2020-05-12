package cn.ann.financial.manager.business.deal.group;

import cn.ann.financial.manager.config.ApplicationListenerConfiguration;
import cn.ann.financial.manager.configuration.resource.server.CommonResourceServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:22:13
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackageClasses = {
        CommonResourceServerConfiguration.class,
        ApplicationListenerConfiguration.class,
        BusinessDealGroupApplication.class,
})
public class BusinessDealGroupApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessDealGroupApplication.class, args);
    }
}
