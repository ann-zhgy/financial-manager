package cn.ann.financial.manager.provider.deal.api;

import cn.ann.financial.manager.commons.provider.service.BaseService;
import cn.ann.financial.manager.provider.deal.domain.TbDeal;
import cn.ann.financial.manager.provider.deal.param.DealCondition;
import com.github.pagehelper.PageInfo;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:17:55
 */
public interface TbDealServiceApi extends BaseService<TbDeal> {

    /**
     * 根据 多个 id 获取 交易记录
     *
     * @param ids id 列表
     * @return {@link List<TbDeal>}
     */
    PageInfo<TbDeal> get(@NotNull List<Long> ids, @NotNull int currPage, @NotNull int count);

    /**
     * 根据 条件查询 交易记录
     *
     * @param condition {@link DealCondition}
     * @return {@link List<TbDeal>}
     */
    List<TbDeal> get(@NotNull DealCondition condition);

    /**
     * 根据 条件查询 交易记录 分页
     *
     * @param condition {@link DealCondition}
     * @return {@link List<TbDeal>}
     */
    PageInfo<TbDeal> get(@NotNull DealCondition condition, @NotNull int currPage, @NotNull int count);

    /**
     * 通过多个 user id 获取交易记录
     *
     * @param userIds {@link List<Long>}
     * @return {@link List<TbDeal>}
     */
    PageInfo<TbDeal> getListByUserIds(@NotNull List<Long> userIds, @NotNull int currPage, @NotNull int count);

    /**
     * 固定时间内多个 user id 的交易记录
     *
     * @param userIds {@link List<Long>}
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return {@link List<TbDeal>}
     */
    List<TbDeal> get(@NotNull List<Long> userIds, @NotNull Date beginTime, @NotNull Date endTime);
}
