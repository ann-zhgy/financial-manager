package cn.ann.financial.manager.business.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * <p>
 * Date: 2020-5-12 20:23
 *
 * @author 88475
 * @version v1.0
 */
@Data
@ConfigurationProperties(prefix="fdfs.base")
public class UploadProperties {
    private List<String> images = new ArrayList<>();
}
