package cn.ann.financial.manager.provider.plan.service;

import cn.ann.financial.manager.commons.constant.ProviderConstant;
import cn.ann.financial.manager.commons.provider.service.impl.BaseServiceImpl;
import cn.ann.financial.manager.provider.plan.api.TbPlanServiceApi;
import cn.ann.financial.manager.provider.plan.domain.TbPlan;
import cn.ann.financial.manager.provider.plan.mapper.TbPlanMapper;
import cn.ann.financial.manager.provider.plan.param.PlanCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:19:00
 */
@Service(version = "1.0.0")
public class TbPlanService extends BaseServiceImpl<TbPlan, TbPlanMapper> implements TbPlanServiceApi {
    @Override
    protected void insertInit(TbPlan plan) {
        if (plan.getStatus() == null
                || (plan.getStatus() != ProviderConstant.PLAN_NO_FINISH
                && plan.getStatus() != ProviderConstant.PLAN_FINISH
                && plan.getStatus() != ProviderConstant.PLAN_CANCEL)) {
            plan.setStatus(ProviderConstant.PLAN_NO_FINISH);
        }
    }

    @Override
    protected void updateInit(TbPlan oldPlan, TbPlan plan) {
        if (StringUtils.isNotBlank(plan.getName())) {
            oldPlan.setName(plan.getName());
        }
        if (plan.getBeginTime() != null) {
            oldPlan.setBeginTime(plan.getBeginTime());
        }
        if (plan.getEndTime() != null) {
            oldPlan.setEndTime(plan.getEndTime());
        }
        if (StringUtils.isNotBlank(plan.getIntro())) {
            oldPlan.setIntro(plan.getIntro());
        }
        if (plan.getMoney() != null) {
            oldPlan.setMoney(plan.getMoney());
        }
        if (plan.getType() != null) {
            oldPlan.setType(plan.getType());
        }
        if (StringUtils.isNotBlank(plan.getRemark())) {
            oldPlan.setRemark(plan.getRemark());
        }
    }

    @Override
    public int updateStatus(@NotNull TbPlan plan) {
        TbPlan oldPlan = get(plan.getId());
        if (plan.getStatus() != null) {
            if (plan.getStatus() == ProviderConstant.PLAN_FINISH) {
                if (plan.getDealId() != null) {
                    oldPlan.setFinishTime(new Date());
                    oldPlan.setDealId(plan.getDealId());
                } else {
                    throw new RuntimeException("计划已完成，但是关联的交易记录为空");
                }
            }
            oldPlan.setStatus(plan.getStatus());
        }
        return mapper.updateByPrimaryKey(oldPlan);
    }

    @Override
    public List<TbPlan> getAvailablePlans(@NotNull Long userId) {
        Example example = new Example(TbPlan.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("status", ProviderConstant.PLAN_NO_FINISH)
                .andIsNull("dealId");
        return mapper.selectByExample(example);
    }

    @Override
    public PageInfo<TbPlan> get(@NotNull List<Long> userIds, @NotNull int currPage, @NotNull int count) {
        PageHelper.startPage(currPage, count);
        Example example = new Example(TbPlan.class);
        example.setDistinct(true);
        example.createCriteria()
                .andEqualTo("open", ProviderConstant.OPEN)
                .andIn("userId", userIds);
        List<TbPlan> plans = mapper.selectByExample(example);
        return new PageInfo<>(plans);
    }

    @Override
    public List<TbPlan> get(PlanCondition condition) {
        return mapper.get(condition);
    }

    @Override
    public PageInfo<TbPlan> get(PlanCondition condition, int currPage, int count) {
        PageHelper.startPage(currPage, count);
        List<TbPlan> plans = mapper.get(condition);
        return new PageInfo<>(plans);
    }

    @Override
    public TbPlan getByDealId(Long dealId) {
        Example example = new Example(TbPlan.class);
        example.createCriteria()
                .andEqualTo("dealId", dealId);

        return mapper.selectOneByExample(example);
    }
}
