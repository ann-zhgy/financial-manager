package cn.ann.financial.manager.business.upload.service;

import cn.ann.financial.manager.business.upload.config.UploadProperties;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：文件上传服务 api 实现
 * <p>
 * Date: 2020-5-3 21:52
 *
 * @author 88475
 * @version v1.0
 */
@Slf4j
@Service
public class UploadService {
    @Resource
    private FastFileStorageClient fileStorageClient;

    @Value("${fdfs.base.url}")
    private String baseUrl;

    @Resource
    private UploadProperties properties;

    public String upload(@NotNull MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
        StorePath storePath = fileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extName, null);
        log.info("=================================================");
        log.info("文件上传成功，路径为：" + storePath.getFullPath());
        return baseUrl + storePath.getFullPath();
    }

    public void delete(@NotNull String filePath) {
        String path = filePath.substring(baseUrl.length());
        if (!properties.getImages().contains(filePath))
            fileStorageClient.deleteFile(path);
    }
}
