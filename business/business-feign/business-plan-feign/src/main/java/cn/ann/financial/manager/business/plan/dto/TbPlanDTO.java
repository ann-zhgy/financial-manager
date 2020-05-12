package cn.ann.financial.manager.business.plan.dto;

import cn.ann.financial.manager.commons.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-25:1:15
 */
@Data
public class TbPlanDTO extends BaseDTO implements Serializable {
    /** 名字 */
    private String name;

    /** 简介 */
    private String intro;

    /** 金额 */
    private Double money;

    /** 交易类型：收入 | 支出 */
    private Integer type;

    /** 计划开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    /** 计划结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /** 计划完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finishTime;

    /** 备注 */
    private String remark;

    /** 状态：1. 已完成 | 2. 未完成（默认） | 3. 已取消 */
    private Integer status;

    /** 创建者ID */
    private Long userId;

    /** 关联的交易记录ID */
    private Long dealId;

    private static final long serialVersionUID = 1L;
}
