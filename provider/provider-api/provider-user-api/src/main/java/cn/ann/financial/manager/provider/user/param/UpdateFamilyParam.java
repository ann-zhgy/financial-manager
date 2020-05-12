package cn.ann.financial.manager.provider.user.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：更新用户家庭关系的参数
 * <p>
 * Date: 2020-4-26 20:36
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class UpdateFamilyParam implements Serializable {
    private static final long serialVersionUID = 464068396430113241L;

    private Long id;
    private Long familyId;
}
