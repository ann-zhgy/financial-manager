package cn.ann.financial.manager.business.profile.dto.param;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 个人信息参数
 * <p>
 * Description:
 * </p>
 */
@Data
public class ProfileParam implements Serializable {
    private static final long serialVersionUID = -6567314585719758798L;
    private Long id;

    /** 昵称 */
    private String nickName;

    /** 性别 */
    private Integer gender;

    /** 生日 */
    private Date birth;

    /** 手机号 */
    private String phone;

    /** 邮箱 */
    private String email;
}
