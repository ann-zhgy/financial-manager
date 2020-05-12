package cn.ann.financial.manager.business.register.dto.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：用户激活参数
 * <p>
 * Date: 2020-5-10 13:28
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class ActiveParam implements Serializable {
    private static final long serialVersionUID = -7054913814052734427L;

    private Long id;
    private String code;
}
