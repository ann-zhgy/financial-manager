package cn.ann.financial.manager.business.family.dto;

import cn.ann.financial.manager.commons.constant.Identity;
import lombok.Data;

import java.io.Serializable;

/**
 * Description：家庭成员（user）传输对象
 * <p>
 * Date: 2020-4-27 21:59
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class MemberDTO implements Serializable {
    private static final long serialVersionUID = 3719550453647941577L;

    private Long id;
    private String name;
    private Integer gender;
    private String phone;
    private String email;
    private Identity identity;
}
