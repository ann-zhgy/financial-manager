package cn.ann.financial.manager.provider.plan.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：计划信息查询条件
 * <p>
 * Date: 2020-5-9 11:18
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class PlanCondition implements Serializable {
    private static final long serialVersionUID = 9189784726179211498L;

    /** 名字 */
    private String name;

    /** 简介 */
    private String intro;

    /** 最小金额 */
    private Double minMoney;

    /** 最大金额 */
    private Double maxMoney;

    /** 交易类型：收入 | 支出 */
    private Integer type;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /** 公开 */
    private Integer open;

    /** 状态：1. 已完成 | 2. 未完成（默认） | 3. 已取消 */
    private Integer status;

    /** 创建者ID */
    private Long userId;
}
