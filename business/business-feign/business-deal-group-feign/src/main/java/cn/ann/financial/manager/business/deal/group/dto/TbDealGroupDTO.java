package cn.ann.financial.manager.business.deal.group.dto;

import cn.ann.financial.manager.commons.dto.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-4-19:16:37
 */
@Data
public class TbDealGroupDTO extends BaseDTO implements Serializable {
    /**
     * 名字
     */
    private String name;

    /**
     * 简介
     */
    private String intro;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 3841494970701715096L;
}
