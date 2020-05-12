package cn.ann.financial.manager.provider.user;

import cn.ann.financial.manager.configuration.sentinel.DubboSentinelConfiguration;
import cn.ann.financial.manager.provider.user.config.UserProperties;
import cn.ann.financial.manager.provider.user.mapper.TbUserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-20:21:09
 */
@SpringBootApplication(scanBasePackageClasses = {
        ProviderUserServiceApplication.class,
        DubboSentinelConfiguration.class
})
@MapperScan(basePackageClasses = {TbUserMapper.class})
@EnableConfigurationProperties(UserProperties.class)
public class ProviderUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUserServiceApplication.class, args);
    }
}
