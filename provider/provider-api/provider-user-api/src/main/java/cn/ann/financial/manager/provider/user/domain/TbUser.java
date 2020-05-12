package cn.ann.financial.manager.provider.user.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:20:03 
 */
@Data
@Table(name = "tb_user")
public class TbUser implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 用户名 */
    @Column(name = "username")
    private String username;

    /** 密码，加密存储 */
    @Column(name = "`password`")
    private String password;

    /** 昵称 */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 性别：male | female
     */
    @Column(name = "gender")
    private Integer gender;

    /** 头像 */
    @Column(name = "icon")
    private String icon;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth")
    private Date birth;

    /** 注册手机号 */
    @Column(name = "phone")
    private String phone;

    /** 是否可用 */
    @Column(name = "enable")
    private Integer enable;

    /** 激活码 */
    @Column(name = "code")
    private String code;

    /** 注册邮箱 */
    @Column(name = "email")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created")
    private Date created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated")
    private Date updated;

    /** 是否公开信息 */
    @Column(name = "`open`")
    private Integer open;

    /** 所属家庭id */
    @Column(name = "family_id")
    private Long familyId;

    private static final long serialVersionUID = 1L;
}