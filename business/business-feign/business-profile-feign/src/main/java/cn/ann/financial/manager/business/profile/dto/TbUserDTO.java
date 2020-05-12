package cn.ann.financial.manager.business.profile.dto;

import cn.ann.financial.manager.commons.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:14:03
 */
@Data
public class TbUserDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 3723930564518122962L;
    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别：male | female
     */
    private Integer gender;

    /**
     * 头像
     */
    private String icon;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 所属家庭id
     */
    private Long familyId;
}
