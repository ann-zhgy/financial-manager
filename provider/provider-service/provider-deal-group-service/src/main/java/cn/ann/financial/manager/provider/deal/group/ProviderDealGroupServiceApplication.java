package cn.ann.financial.manager.provider.deal.group;

import cn.ann.financial.manager.configuration.sentinel.DubboSentinelConfiguration;
import cn.ann.financial.manager.provider.deal.group.mapper.TbDealGroupMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:20:27
 */
@SpringBootApplication(scanBasePackageClasses = {
        ProviderDealGroupServiceApplication.class,
        DubboSentinelConfiguration.class
})
@MapperScan(basePackageClasses = {TbDealGroupMapper.class})
public class ProviderDealGroupServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderDealGroupServiceApplication.class, args);
    }
}
