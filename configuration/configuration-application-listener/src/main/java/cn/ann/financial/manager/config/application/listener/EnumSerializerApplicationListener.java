package cn.ann.financial.manager.config.application.listener;

import cn.ann.financial.manager.commons.constant.Identity;
import cn.ann.financial.manager.commons.constant.serializer.IdentitySerializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Description：枚举类序列化的 application listener
 * <p>
 * Date: 2020-4-29 11:23
 *
 * @author 88475
 * @version v1.0
 */
@Component
public class EnumSerializerApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private boolean isExecute;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(!isExecute) {
            ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
            SimpleModule module = new SimpleModule();
            JsonSerializer<Identity> serializer = new IdentitySerializer();
            module.addSerializer(serializer);
            ObjectMapper bean = applicationContext.getBean(ObjectMapper.class);
            bean.registerModule(module);
        }
        isExecute = true;
    }
}
