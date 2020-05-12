package cn.ann.financial.manager.provider.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:22:26
 */
@Configuration
public class UserServiceConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
