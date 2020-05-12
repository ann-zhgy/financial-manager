package cn.ann.financial.manager.business.deal.dto;

import cn.ann.financial.manager.commons.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-4-5:15:25
 */
@Data
public class TbDealDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 3510725467371035794L;

    /** 简介 */
    private String intro;

    /** 金额 */
    private Double money;

    /** 类型：收入 | 支出 */
    private Integer type;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dealTime;

    /** 备注 */
    private String remark;

    /** 创建者ID */
    private Long userId;

    /** 收支组 */
    private Long dealGroup;

    /** 所属计划 */
    private Long planId;
}
