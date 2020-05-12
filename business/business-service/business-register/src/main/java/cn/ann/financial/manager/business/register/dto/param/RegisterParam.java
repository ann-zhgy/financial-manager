package cn.ann.financial.manager.business.register.dto.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：注册需要接收的参数
 * <p>
 * Date: 2020-5-6 23:40
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class RegisterParam implements Serializable {
    private static final long serialVersionUID = 8408348457784673762L;

    private String username;

    private String password;

    private String email;
}
