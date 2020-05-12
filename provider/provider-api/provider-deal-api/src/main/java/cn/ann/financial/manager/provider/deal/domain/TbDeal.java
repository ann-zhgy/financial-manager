package cn.ann.financial.manager.provider.deal.domain;

import cn.ann.financial.manager.commons.provider.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:20:26 
 */
@Data
@Table(name = "tb_deal")
public class TbDeal extends BaseDomain implements Serializable {
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
     * 类型：收入 | 支出
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "deal_time")
    private Date dealTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建者ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 收支组
     */
    @Column(name = "deal_group")
    private Long dealGroup;

    /**
     * 所属计划
     */
    @Column(name = "plan_id")
    private Long planId;

    private static final long serialVersionUID = 1L;
}