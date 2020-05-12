package cn.ann.financial.manager.provider.plan.mapper;

import cn.ann.financial.manager.provider.plan.domain.TbPlan;
import cn.ann.financial.manager.provider.plan.param.PlanCondition;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.TkMapper;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:20:26 
 */
public interface TbPlanMapper extends TkMapper<TbPlan> {
    List<TbPlan> get(PlanCondition condition);
}