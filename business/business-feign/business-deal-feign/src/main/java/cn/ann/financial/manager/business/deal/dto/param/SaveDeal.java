package cn.ann.financial.manager.business.deal.dto.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：创建交易的参数
 * <p>
 * Date: 2020-5-8 22:36
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class SaveDeal implements Serializable {
    private static final long serialVersionUID = -3290685352155517743L;

    /** id */
    private Long id;

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

    /** 收支组 */
    private Long dealGroup;

    /** 所属计划 */
    private Long planId;
}
