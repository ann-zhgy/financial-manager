package cn.ann.financial.manager.business.deal.group.dto.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：更新交易组参数
 * <p>
 * Date: 2020-4-24 14:58
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class ModifyDealGroup implements Serializable {
    private static final long serialVersionUID = 680327809283675566L;

    private Long id;

    private String name;

    private String intro;

    private String remark;

    private Integer open;
}
