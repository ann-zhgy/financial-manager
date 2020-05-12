package cn.ann.financial.manager.business.profile.dto.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改头像参数
 * <p>
 * Description:
 * </p>
 */
@Data
public class IconParam implements Serializable {
    private static final long serialVersionUID = -5201064442138072587L;

    /** 头像地址 */
    private String path;

}
