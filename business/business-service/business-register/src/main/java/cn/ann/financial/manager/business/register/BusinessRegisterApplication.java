package cn.ann.financial.manager.business.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:3:35
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BusinessRegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessRegisterApplication.class, args);
    }
}
