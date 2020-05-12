package cn.ann.financial.manager.business.plan;

import cn.ann.financial.manager.config.ApplicationListenerConfiguration;
import cn.ann.financial.manager.configuration.resource.server.CommonResourceServerConfiguration;
import cn.ann.financial.manager.configuration.resource.server.service.PermissionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-24:21:15
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackageClasses = {
        CommonResourceServerConfiguration.class,
        ApplicationListenerConfiguration.class,
        BusinessPlanApplication.class,
})
public class BusinessPlanApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessPlanApplication.class, args);
    }
}
