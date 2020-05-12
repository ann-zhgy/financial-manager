package cn.ann.financial.manager.business.family.dto.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：更新家庭参数
 * <p>
 * Date: 2020-4-26 20:20
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class ModifyFamily implements Serializable {
    private static final long serialVersionUID = -862530167636762472L;

    private Long id;
    private String name;
    private String intro;
}
