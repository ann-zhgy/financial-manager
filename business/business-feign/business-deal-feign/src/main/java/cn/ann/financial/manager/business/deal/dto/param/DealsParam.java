package cn.ann.financial.manager.business.deal.dto.param;

import cn.ann.financial.manager.provider.deal.param.DealCondition;
import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * Description：获取 交易记录 列表 的参数
 * <p>
 * Date: 2020-5-9 10:00
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class DealsParam implements Serializable {
    private static final long serialVersionUID = 6058057450418341982L;

    @NotNull
    private int pageNum;

    private Integer pageCount;

    private DealCondition condition;
}
