package cn.ann.financial.manager.business.family.dto;

import cn.ann.financial.manager.commons.constant.Identity;
import cn.ann.financial.manager.commons.dto.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Description：TbFamily 数据传输对象
 * <p>
 * Date: 2020-4-26 18:44
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class TbFamilyDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -6564264442606358559L;

    /** id */
    private Long id;

    /** 名字 */
    private String name;

    /** 用户数量 */
    private Integer userCount;

    /** 介绍 */
    private String intro;

    /** 当前用户身份 */
    private Identity identity;
}
