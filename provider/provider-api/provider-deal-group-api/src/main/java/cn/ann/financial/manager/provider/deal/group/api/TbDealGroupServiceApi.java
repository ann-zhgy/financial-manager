package cn.ann.financial.manager.provider.deal.group.api;

import cn.ann.financial.manager.commons.provider.service.BaseService;
import cn.ann.financial.manager.provider.deal.group.domain.TbDealGroup;
import cn.ann.financial.manager.provider.deal.group.param.DealGroupCondition;
import com.github.pagehelper.PageInfo;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:18:05
 */
public interface TbDealGroupServiceApi extends BaseService<TbDealGroup> {
    List<TbDealGroup> get(@NotNull DealGroupCondition condition);

    PageInfo<TbDealGroup> get(@NotNull DealGroupCondition condition, @NotNull Integer currPage, @NotNull Integer count);

}
