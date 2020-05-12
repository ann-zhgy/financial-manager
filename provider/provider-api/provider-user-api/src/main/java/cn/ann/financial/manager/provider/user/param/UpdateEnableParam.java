package cn.ann.financial.manager.provider.user.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：更新 账号可用 参数
 * <p>
 * Date: 2020-5-10 0:07
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class UpdateEnableParam implements Serializable {
    private static final long serialVersionUID = 3890869311389295293L;

    private Long id;
    private Integer enable;
}
