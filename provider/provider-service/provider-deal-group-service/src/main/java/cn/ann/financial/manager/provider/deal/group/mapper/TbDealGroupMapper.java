package cn.ann.financial.manager.provider.deal.group.mapper;

import cn.ann.financial.manager.provider.deal.group.domain.TbDealGroup;
import cn.ann.financial.manager.provider.deal.group.param.DealGroupCondition;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.TkMapper;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:20:26 
 */
public interface TbDealGroupMapper extends TkMapper<TbDealGroup> {
    List<TbDealGroup> get(DealGroupCondition condition);
}