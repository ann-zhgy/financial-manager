package cn.ann.financial.manager.business.plan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：通过家庭获取 计划 的 数据传输对象
 * <p>
 * Date: 2020-5-12 2:04
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class FamilyPlanDTO implements Serializable {
    private static final long serialVersionUID = 3722072013404986425L;

    /** 名字 */
    private String name;

    /** 简介 */
    private String intro;

    /** 金额 */
    private Double money;

    /** 交易类型：收入 | 支出 */
    private Integer type;

    /** 计划完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finishTime;

    /** 备注 */
    private String remark;

    /** 状态：1. 已完成 | 2. 未完成（默认） | 3. 已取消 */
    private Integer status;

    private String username;
}
