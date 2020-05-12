package cn.ann.financial.manager.provider.deal.mapper;


import cn.ann.financial.manager.provider.deal.domain.TbDeal;
import cn.ann.financial.manager.provider.deal.param.DealCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.validation.annotation.Validated;
import tk.mybatis.mapper.TkMapper;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:20:26 
 */
public interface TbDealMapper extends TkMapper<TbDeal> {
    List<TbDeal> get(DealCondition condition);
}