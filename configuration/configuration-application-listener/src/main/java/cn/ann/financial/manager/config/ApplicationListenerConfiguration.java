package cn.ann.financial.manager.config;

import cn.ann.financial.manager.config.application.listener.EnumSerializerApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description：application listener 配置类
 * <p>
 * Date: 2020-4-29 11:22
 *
 * @author 88475
 * @version v1.0
 */
@Configuration
public class ApplicationListenerConfiguration {
    @Bean
    public EnumSerializerApplicationListener applicationListener() {
        return new EnumSerializerApplicationListener();
    }
}
