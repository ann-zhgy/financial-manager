package cn.ann.financial.manager.provider.deal;

import cn.ann.financial.manager.configuration.sentinel.DubboSentinelConfiguration;
import cn.ann.financial.manager.provider.deal.mapper.TbDealMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:23:25
 */
@SpringBootApplication(scanBasePackageClasses = {
        ProviderDealServiceApplication.class,
        DubboSentinelConfiguration.class
})
@MapperScan(basePackageClasses = {TbDealMapper.class})
public class ProviderDealServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderDealServiceApplication.class, args);
    }
}
