package cn.ann.financial.manager.business.deal;

import cn.ann.financial.manager.config.ApplicationListenerConfiguration;
import cn.ann.financial.manager.configuration.resource.server.CommonResourceServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackageClasses = {
        CommonResourceServerConfiguration.class,
        ApplicationListenerConfiguration.class,
        BusinessDealApplication.class,
})
public class BusinessDealApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessDealApplication.class, args);
    }
}
