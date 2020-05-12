package cn.ann.financial.manager.provider.deal.group.domain;

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
@Table(name = "tb_deal_group")
public class TbDealGroup extends BaseDomain implements Serializable {
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
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建者ID
     */
    @Column(name = "user_id")
    private Long userId;

    private static final long serialVersionUID = 1L;
}