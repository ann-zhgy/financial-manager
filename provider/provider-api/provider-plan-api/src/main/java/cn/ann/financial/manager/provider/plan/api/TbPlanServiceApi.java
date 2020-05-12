package cn.ann.financial.manager.provider.plan.api;

import cn.ann.financial.manager.commons.provider.service.BaseService;
import cn.ann.financial.manager.provider.plan.domain.TbPlan;
import cn.ann.financial.manager.provider.plan.param.PlanCondition;
import com.github.pagehelper.PageInfo;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:17:37
 */
public interface TbPlanServiceApi extends BaseService<TbPlan> {

    int updateStatus(@NotNull TbPlan plan);

    List<TbPlan> getAvailablePlans(@NotNull Long userId);

    List<TbPlan> get(@NotNull List<Long> userIds);

    List<TbPlan> get(@NotNull PlanCondition condition);

    PageInfo<TbPlan> get(@NotNull PlanCondition condition, @NotNull int currPage, @NotNull int count);

    TbPlan getByDealId(@NotNull Long dealId);
}
