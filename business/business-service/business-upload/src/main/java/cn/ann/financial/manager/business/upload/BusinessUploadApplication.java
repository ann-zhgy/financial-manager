package cn.ann.financial.manager.business.upload;

import cn.ann.financial.manager.business.upload.config.UploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description：文件上传服务启动类
 * <p>
 * Date: 2020-5-12 4:05
 *
 * @author 88475
 * @version v1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({UploadProperties.class})
public class BusinessUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessUploadApplication.class, args);
    }
}
