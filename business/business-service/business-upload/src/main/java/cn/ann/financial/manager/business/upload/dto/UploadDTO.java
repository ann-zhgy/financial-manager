package cn.ann.financial.manager.business.upload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description：上传文件 数据传输对象
 * <p>
 * Date: 2020-5-12 12:25
 *
 * @author 88475
 * @version v1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadDTO implements Serializable {
    private static final long serialVersionUID = 7601485916541570003L;

    private String path;

    private List<String> paths;
}
