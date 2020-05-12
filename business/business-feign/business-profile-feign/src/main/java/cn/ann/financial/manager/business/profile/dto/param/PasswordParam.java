package cn.ann.financial.manager.business.profile.dto.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码参数
 * <p>
 * Description:
 * </p>
 */
@Data
public class PasswordParam implements Serializable {
    private static final long serialVersionUID = -2632678263157048663L;

    private String oldPassword;
    private String newPassword;

}
