package cn.ann.financial.manager.provider.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：读取 配置文件中的 bean
 * <p>
 * Date: 2020-5-12 20:12
 *
 * @author 88475
 * @version v1.0
 */
@Data
@ConfigurationProperties(prefix="fdfs.base")
public class UserProperties {
    private List<String> images = new ArrayList<>();
}
