package cn.ann.financial.manager.provider.user.domain;

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
@Table(name = "tb_role")
public class TbRole implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父角色
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 角色名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 角色英文名称
     */
    @Column(name = "en_name")
    private String enName;

    /**
     * 备注
     */
    @Column(name = "description")
    private String description;

    private static final long serialVersionUID = 1L;
}