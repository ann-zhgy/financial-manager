package cn.ann.financial.manager.provider.family;

import cn.ann.financial.manager.configuration.sentinel.DubboSentinelConfiguration;
import cn.ann.financial.manager.provider.family.mapper.TbFamilyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:23:34
 */
@SpringBootApplication(scanBasePackageClasses = {
        ProviderFamilyServiceApplication.class,
        DubboSentinelConfiguration.class
})
@MapperScan(basePackageClasses = {TbFamilyMapper.class})
public class ProviderFamilyServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderFamilyServiceApplication.class, args);
    }
}
