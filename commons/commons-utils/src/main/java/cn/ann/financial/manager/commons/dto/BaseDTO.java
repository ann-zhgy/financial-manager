package cn.ann.financial.manager.commons.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-25:1:29
 */
@Data
public class BaseDTO {
    private Long id;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    /**
     * 最后一次更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updated;

    /**
     * 是否公开信息
     */
    private Integer open;
}
