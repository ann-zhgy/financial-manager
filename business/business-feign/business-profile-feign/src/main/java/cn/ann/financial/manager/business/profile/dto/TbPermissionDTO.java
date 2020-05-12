package cn.ann.financial.manager.business.profile.dto;

import cn.ann.financial.manager.commons.dto.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-24:22:33
 */
@Data
public class TbPermissionDTO implements Serializable {
    private static final long serialVersionUID = 4629225086558331020L;

    private Long id;

    /**
     * 父权限
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限英文名称
     */
    private String enName;

    /**
     * 授权路径
     */
    private String url;

}
