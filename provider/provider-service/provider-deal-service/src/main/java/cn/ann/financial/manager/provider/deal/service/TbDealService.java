package cn.ann.financial.manager.provider.deal.service;

import cn.ann.financial.manager.commons.constant.ProviderConstant;
import cn.ann.financial.manager.commons.provider.service.impl.BaseServiceImpl;
import cn.ann.financial.manager.provider.deal.api.TbDealServiceApi;
import cn.ann.financial.manager.provider.deal.domain.TbDeal;
import cn.ann.financial.manager.provider.deal.mapper.TbDealMapper;
import cn.ann.financial.manager.provider.deal.param.DealCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.istack.internal.NotNull;
import org.apache.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:23:30
 */
@Service(version = "1.0.0")
public class TbDealService extends BaseServiceImpl<TbDeal, TbDealMapper> implements TbDealServiceApi {

    @Override
    protected void insertInit(TbDeal tbDeal) {
    }

    @Override
    protected void updateInit(TbDeal oldDeal, TbDeal tbDeal) {
        if (tbDeal.getDealGroup() != null) {
            oldDeal.setDealGroup(tbDeal.getDealGroup());
        }
        if (tbDeal.getIntro() != null) {
            oldDeal.setIntro(tbDeal.getIntro());
        }
    }

    @Override
    public PageInfo<TbDeal> get(List<Long> ids, @NotNull int currPage, @NotNull int count) {
        PageHelper.startPage(currPage, count);
        Example example = new Example(TbDeal.class);
        example.setDistinct(true);
        example.createCriteria()
                .andEqualTo("open", ProviderConstant.OPEN)
                .andIn("userId", ids);
        List<TbDeal> list = mapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public List<TbDeal> get(@NotNull DealCondition condition) {
        return mapper.get(condition);
    }

    @Override
    public PageInfo<TbDeal> get(DealCondition condition, int currPage, int count) {
        PageHelper.startPage(currPage, count);
        List<TbDeal> list = mapper.get(condition);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<TbDeal> getListByUserIds(List<Long> userIds, int currPage, int count) {
        Example example = new Example(TbDeal.class);
        example.setDistinct(true);
        example.createCriteria()
                .andIn("userId", userIds);

        PageHelper.startPage(currPage, count);
        List<TbDeal> list = mapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public List<TbDeal> get(List<Long> userIds, Date beginTime, Date endTime) {
        Example example = new Example(TbDeal.class);
        example.setDistinct(true);
        example.createCriteria()
                .andGreaterThanOrEqualTo("dealTime", beginTime)
                .andLessThanOrEqualTo("dealTime", endTime)
                .andIn("userId", userIds);

        return mapper.selectByExample(example);
    }
}
