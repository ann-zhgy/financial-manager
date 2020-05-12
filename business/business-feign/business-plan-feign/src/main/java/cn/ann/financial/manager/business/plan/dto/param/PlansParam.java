package cn.ann.financial.manager.business.plan.dto.param;

import cn.ann.financial.manager.provider.plan.param.PlanCondition;
import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * Description：获取计划参数
 * <p>
 * Date: 2020-5-9 11:14
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class PlansParam implements Serializable {
    private static final long serialVersionUID = 6018185575699875009L;

    private PlanCondition condition;

    @NotNull
    private int pageNum;
    private Integer pageCount;

}
