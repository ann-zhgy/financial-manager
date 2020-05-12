package cn.ann.financial.manager.business.deal.group.dto.param;

import cn.ann.financial.manager.provider.deal.group.param.DealGroupCondition;
import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * Description：交易组 查询 参数
 * <p>
 * Date: 2020-5-9 20:16
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class DealGroupsParam implements Serializable {
    private static final long serialVersionUID = 6570510415318435285L;

    private DealGroupCondition condition;

    @NotNull
    private int pageNum;
    private Integer pageCount;
}
