package cn.ann.financial.manager.provider.user.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：更新 公开 信息 参数
 * <p>
 * Date: 2020-5-10 0:07
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class UpdateOpenParam implements Serializable {
    private static final long serialVersionUID = 8383401503223529688L;

    private Long id;
    private Integer open;
}
