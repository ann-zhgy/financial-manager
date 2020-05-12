package cn.ann.financial.manager.provider.user.domain;

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
 * Description：
 * 
 * Date: 2020-5-4 16:41
 * @author 88475
 * @version v1.0
 */

/**
    * 权限表
    */
@Data
@Table(name = "tb_permission")
public class TbPermission implements Serializable {
    private static final long serialVersionUID = -6675028594313668601L;

    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created")
    private Date created;

    /**
     * 最后一次更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated")
    private Date updated;

    /**
     * 父权限
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 权限英文名称
     */
    @Column(name = "en_name")
    private String enName;

    /**
     * 授权路径
     */
    @Column(name = "url")
    private String url;

    /**
     * 备注
     */
    @Column(name = "description")
    private String description;

    /**
     * 是否展示在管理列表中
     */
    @Column(name = "display")
    private Integer display;
}