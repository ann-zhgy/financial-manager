package cn.ann.financial.manager.provider.plan;

import cn.ann.financial.manager.configuration.sentinel.DubboSentinelConfiguration;
import cn.ann.financial.manager.provider.plan.mapper.TbPlanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:19:37
 */
@SpringBootApplication(scanBasePackageClasses = {
        ProviderPlanServiceApplication.class,
        DubboSentinelConfiguration.class
})
@MapperScan(basePackageClasses = {TbPlanMapper.class})
public class ProviderPlanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPlanServiceApplication.class, args);
    }
}
