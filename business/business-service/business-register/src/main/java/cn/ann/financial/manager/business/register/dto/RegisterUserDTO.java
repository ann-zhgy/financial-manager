package cn.ann.financial.manager.business.register.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：注册后返回 用户 DTO
 * <p>
 * Date: 2020-5-11 20:18
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class RegisterUserDTO implements Serializable {
    private static final long serialVersionUID = 1152390325629298846L;

    private Long id;
    private String username;
}
