package cn.ann.financial.manager.provider.family.domain;

import cn.ann.financial.manager.commons.provider.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:20:26 
 */
@Data
@Table(name = "tb_family")
public class TbFamily extends BaseDomain implements Serializable {
    /**
     * 名字
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 用户数量
     */
    @Column(name = "user_count")
    private Integer userCount;

    /**
     * 介绍
     */
    @Column(name = "intro")
    private String intro;

    /**
     * 创建者ID
     */
    @Column(name = "user_id")
    private Long userId;

    private static final long serialVersionUID = 1L;
}