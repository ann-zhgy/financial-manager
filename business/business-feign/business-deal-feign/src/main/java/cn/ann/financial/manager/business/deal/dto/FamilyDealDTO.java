package cn.ann.financial.manager.business.deal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：通过家庭获取交易信息的数据传输对象
 * <p>
 * Date: 2020-5-11 23:30
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class FamilyDealDTO implements Serializable {
    private static final long serialVersionUID = -464652983481868183L;

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
    private String username;
}
