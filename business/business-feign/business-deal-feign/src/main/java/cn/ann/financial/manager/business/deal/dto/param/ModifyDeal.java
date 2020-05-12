package cn.ann.financial.manager.business.deal.dto.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：更新时的deal参数
 * <p>
 * Date: 2020-4-23 11:53
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class ModifyDeal implements Serializable {
    private static final long serialVersionUID = -7967616174103511416L;

    private Long id;

    private String intro;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dealTime;

    private Double money;

    private Integer type;

    private String remark;

    private Integer open;

    private Long dealGroup;

    private Long planId;
}
