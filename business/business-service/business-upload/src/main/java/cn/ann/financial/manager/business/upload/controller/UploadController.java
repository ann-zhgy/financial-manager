package cn.ann.financial.manager.business.upload.controller;

import cn.ann.financial.manager.business.upload.dto.UploadDTO;
import cn.ann.financial.manager.business.upload.service.UploadService;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：文件上传 控制器
 * <p>
 * Date: 2020-5-12 4:13
 *
 * @author 88475
 * @version v1.0
 */
@RestController
@RequestMapping("fast/dfs")
public class UploadController {
    @Resource
    private UploadService uploadService;

    @PostMapping("upload")
    public ResponseResult<UploadDTO> upload(@RequestBody MultipartFile file, @RequestBody MultipartFile[] files) throws IOException {
        UploadDTO dto = new UploadDTO();
        if (file != null) {
            dto.setPath(uploadService.upload(file));
        }
        if (files != null && files.length > 0) {
            List<String> fileNames = new ArrayList<>();
            for (MultipartFile editorFile : files) {
                fileNames.add(uploadService.upload(editorFile));
            }

            dto.setPaths(fileNames);
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "文件上传成功", dto);
    }

    @PostMapping("delete")
    public ResponseResult<Void> delete(@RequestBody String url) {
        try {
            uploadService.delete(url);
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "删除文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "删除文件失败");
        }
    }
}
