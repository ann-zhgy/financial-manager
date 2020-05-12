package cn.ann.financial.manager.provider.deal.group.service;

import cn.ann.financial.manager.commons.provider.service.impl.BaseServiceImpl;
import cn.ann.financial.manager.provider.deal.group.api.TbDealGroupServiceApi;
import cn.ann.financial.manager.provider.deal.group.domain.TbDealGroup;
import cn.ann.financial.manager.provider.deal.group.mapper.TbDealGroupMapper;
import cn.ann.financial.manager.provider.deal.group.param.DealGroupCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:20:20
 */
@Service(version = "1.0.0")
public class TbDealGroupService extends BaseServiceImpl<TbDealGroup, TbDealGroupMapper> implements TbDealGroupServiceApi {
    @Override
    protected void insertInit(TbDealGroup tbDealGroup) {
    }

    @Override
    protected void updateInit(TbDealGroup oldDealGroup, TbDealGroup dealGroup) {
        if (StringUtils.isNotBlank(dealGroup.getIntro())) {
            oldDealGroup.setIntro(dealGroup.getIntro());
        }
        if (StringUtils.isNotBlank(dealGroup.getName())) {
            oldDealGroup.setName(dealGroup.getName());
        }
        if (StringUtils.isNotBlank(dealGroup.getRemark())) {
            oldDealGroup.setRemark(dealGroup.getRemark());
        }
    }

    @Override
    public List<TbDealGroup> get(DealGroupCondition condition) {
        return mapper.get(condition);
    }

    @Override
    public PageInfo<TbDealGroup> get(DealGroupCondition condition, Integer currPage, Integer count) {
        PageHelper.startPage(currPage, count);
        List<TbDealGroup> groups = mapper.get(condition);
        return new PageInfo<>(groups);
    }
}
