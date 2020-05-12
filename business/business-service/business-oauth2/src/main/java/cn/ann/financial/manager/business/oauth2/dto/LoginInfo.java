package cn.ann.financial.manager.business.oauth2.dto;

import cn.ann.financial.manager.commons.constant.Identity;
import lombok.Data;

import java.io.Serializable;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:10:41
 */
@Data
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = -1582070235500376424L;

    private String name;
    private String avatar;
    private String token;
    private Identity identity;
}
