package cn.ann.financial.manager.provider.plan.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import cn.ann.financial.manager.commons.provider.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:20:26 
 */
@Data
@Table(name = "tb_plan")
public class TbPlan extends BaseDomain implements Serializable {
    /**
     * 名字
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 简介
     */
    @Column(name = "intro")
    private String intro;

    /**
     * 金额
     */
    @Column(name = "money")
    private Double money;

    /**
     * 交易类型：收入 | 支出
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 状态：1. 已完成 | 2. 未完成（默认） | 3. 已取消
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 创建者ID
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 关联 交易记录ID
     */
    @Column(name = "deal_id")
    private Long dealId;

    private static final long serialVersionUID = 1L;
}