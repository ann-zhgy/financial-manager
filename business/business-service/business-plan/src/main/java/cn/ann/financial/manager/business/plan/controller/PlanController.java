package cn.ann.financial.manager.business.plan.controller;

import cn.ann.financial.manager.business.plan.controller.fallback.PlanControllerFallback;
import cn.ann.financial.manager.business.plan.dto.FamilyPlanDTO;
import cn.ann.financial.manager.business.plan.dto.TbPlanDTO;
import cn.ann.financial.manager.business.plan.dto.param.ModifyPlan;
import cn.ann.financial.manager.business.plan.dto.param.ModifyStatus;
import cn.ann.financial.manager.business.plan.dto.param.PlansParam;
import cn.ann.financial.manager.commons.constant.PageConstant;
import cn.ann.financial.manager.commons.constant.ProviderConstant;
import cn.ann.financial.manager.commons.dto.PageInfoDTO;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import cn.ann.financial.manager.commons.provider.dto.param.ModifyOpen;
import cn.ann.financial.manager.provider.deal.api.TbDealServiceApi;
import cn.ann.financial.manager.provider.deal.domain.TbDeal;
import cn.ann.financial.manager.provider.plan.api.TbPlanServiceApi;
import cn.ann.financial.manager.provider.plan.domain.TbPlan;
import cn.ann.financial.manager.provider.plan.param.PlanCondition;
import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-25:0:52
 */
@RestController
@RequestMapping("plan")
public class PlanController {
    @Reference(version = "1.0.0")
    private TbPlanServiceApi planService;

    @Reference(version = "1.0.0")
    private TbDealServiceApi dealService;

    @Reference(version = "1.0.0")
    private TbUserServiceApi userService;

    /**
     * 保存计划
     *
     * @param plan 要保存的对象 {@link TbPlan}
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "save")
    @SentinelResource(value = "save", fallback = "saveFallback", fallbackClass = PlanControllerFallback.class)
    public ResponseResult<Void> save(@RequestBody TbPlan plan) {
        plan.setUserId(this.getUserId());
        InsertResult<TbPlan> result = planService.insert(plan);
        if (result.getModifyCount() > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "创建计划成功");
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "创建计划失败");
        }
    }

    /**
     * 获取用户的所有计划
     *
     * @param condition {@link PlanCondition}
     * @return {@link ResponseResult<List<TbPlanDTO>>}
     */
    @PostMapping(value = "list")
    @SentinelResource(value = "getPlans", fallback = "getPlansFallback", fallbackClass = PlanControllerFallback.class)
    public ResponseResult<List<TbPlanDTO>> getPlans(@RequestBody(required = false) PlanCondition condition) {
        if (condition == null) {
            condition = new PlanCondition();
        }
        condition.setUserId(this.getUserId());
        List<TbPlan> plans = planService.get(condition);
        List<TbPlanDTO> collect = plans.stream()
                .map(plan -> {
                    TbPlanDTO planDTO = new TbPlanDTO();
                    BeanUtils.copyProperties(plan, planDTO);
                    return planDTO;
                }).collect(Collectors.toList());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取我的全部计划成功", collect);
    }

    /**
     * 获取用户的所有计划  分页
     *
     * @param param {@link PlansParam}
     * @return {@link ResponseResult<PageInfoDTO<TbPlanDTO>>}
     */
    @PostMapping(value = "list/page")
    @SentinelResource(value = "getPlans", fallback = "getPlansFallback", fallbackClass = PlanControllerFallback.class)
    public ResponseResult<PageInfoDTO<TbPlanDTO>> getPlans(@RequestBody @Valid PlansParam param) {
        if (param.getCondition() == null) {
            param.setCondition(new PlanCondition());
        }
        param.getCondition().setUserId(this.getUserId());
        PageInfo<TbPlan> info = planService.get(
                param.getCondition(), param.getPageNum(),
                param.getPageCount() == null ? PageConstant.PAGE_SIZE : param.getPageCount());
        PageInfoDTO<TbPlanDTO> dto = new PageInfoDTO<>();
        BeanUtils.copyProperties(info, dto);
        dto.setList(info.getList().stream()
                .map(tbPlan -> {
                    TbPlanDTO planDTO = new TbPlanDTO();
                    BeanUtils.copyProperties(tbPlan, planDTO);
                    return planDTO;
                }).collect(Collectors.toList()));
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取我的全部计划成功", dto);
    }

    /**
     * 通过 id 获取计划
     *
     * @param id id
     * @return {@link ResponseResult<TbPlan>}
     */
    @GetMapping(value = "/{id}")
    @SentinelResource(value = "getPlan", fallback = "getPlanFallback", fallbackClass = PlanControllerFallback.class)
    public ResponseResult<TbPlanDTO> getPlan(@PathVariable Long id) {
        TbPlan tbPlan = planService.get(id);
        TbPlanDTO dto = new TbPlanDTO();
        BeanUtils.copyProperties(tbPlan, dto);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取计划成功", dto);
    }

    /**
     * 修改计划信息
     *
     * @param param 计划信息
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "modify")
    @SentinelResource(value = "updatePlan", fallback = "updatePlanFallback", fallbackClass = PlanControllerFallback.class)
    public ResponseResult<Void> updatePlan(@RequestBody ModifyPlan param) {
        // 如果参数中有关联交易记录 id，就从数据库中查询
        if (param.getDealId() != null) {
            TbDeal deal = dealService.get(param.getDealId());
            if (deal != null) {
                // 如果查询出来的交易记录 和 参数中关联项不一致，就更新交易记录
                if (!(deal.getMoney().equals(param.getMoney())
                        && deal.getType().equals(param.getType())
                        && deal.getOpen().equals(param.getOpen()))) {
                    deal.setMoney(param.getMoney());
                    deal.setType(param.getType());
                    deal.setOpen(param.getOpen());
                    dealService.update(deal);
                }
            }
        }
        TbPlan plan = new TbPlan();
        BeanUtils.copyProperties(param, plan);
        int result = planService.update(plan);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新计划成功");
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新计划失败");
    }

    /**
     * 更新计划状态
     *
     * @param param {@link ModifyStatus}
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "status")
    @SentinelResource(value = "updateStatus", fallback = "updateStatusFallback", fallbackClass = PlanControllerFallback.class)
    public ResponseResult<Void> updateStatus(@RequestBody ModifyStatus param) {
        TbPlan plan;
        if (param.getStatus() == ProviderConstant.PLAN_FINISH) {
            plan = planService.get(param.getId());
            if (plan.getDealId() == null) {
                TbDeal deal = new TbDeal();
                deal.setIntro("关联计划：" + plan.getIntro());
                deal.setDealTime(new Date());
                deal.setMoney(plan.getMoney());
                deal.setType(plan.getType());
                deal.setPlanId(plan.getId());
                deal.setUserId(plan.getUserId());
                InsertResult<TbDeal> result = dealService.insert(deal);
                plan.setDealId(result.getT().getId());
                plan.setStatus(param.getStatus());
            }
        } else {
            plan = new TbPlan();
            plan.setId(param.getId());
            plan.setStatus(param.getStatus());
        }

        int result = planService.updateStatus(plan);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新计划状态成功");
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新计划状态失败");
    }

    /**
     * 设置 计划 是否公开
     *
     * @param param {@link ModifyOpen}
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "open")
    @SentinelResource(value = "updateOpen", fallback = "updateOpenFallback", fallbackClass = PlanControllerFallback.class)
    public ResponseResult<Void> updateOpen(@RequestBody ModifyOpen param) {
        TbPlan oldPlan = planService.get(param.getId());
        if (oldPlan.getDealId() != null) {
            TbDeal deal = new TbDeal();
            deal.setId(oldPlan.getDealId());
            deal.setOpen(param.getOpen());
            dealService.updateOpen(deal);
        }
        TbPlan plan = new TbPlan();
        plan.setId(param.getId());
        plan.setOpen(param.getOpen());
        int result = planService.updateOpen(plan);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改计划公开状态成功");
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改计划公开状态失败");
    }

    /**
     * 获取可以关联的 计划列表
     *
     * @return {@link ResponseResult<List<TbPlanDTO>>}
     */
    @GetMapping(value = "available")
    @SentinelResource(value = "getAvailablePlans", fallback = "getAvailablePlansFallback", fallbackClass = PlanControllerFallback.class)
    public ResponseResult<List<TbPlanDTO>> getAvailablePlans() {
        List<TbPlan> plans = planService.getAvailablePlans(this.getUserId());
        List<TbPlanDTO> planDTOs = Lists.newArrayList();
        plans.forEach(plan -> {
            TbPlanDTO planDTO = new TbPlanDTO();
            BeanUtils.copyProperties(plan, planDTO);
            planDTOs.add(planDTO);
        });
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "可以关联的计划列表", planDTOs);
    }

    /**
     * 获取家庭成员公开的计划。
     *
     * @return {@link ResponseResult<List<TbPlanDTO>>}
     */
    @GetMapping(value = "family/{pageNum}")
    @SentinelResource(value = "getMemberPlans", fallback = "getMemberPlansFallback", fallbackClass = PlanControllerFallback.class)
    public ResponseResult<PageInfoDTO<FamilyPlanDTO>> getMemberPlans(@PathVariable int pageNum) {
        List<Long> ids = userService.getIdsByFamilyId(this.getUser().getFamilyId());
        List<TbUser> users = ids.stream().map(id -> userService.get(id)).collect(Collectors.toList());
        PageInfo<TbPlan> info = planService.get(ids, pageNum, 10);
        PageInfoDTO<FamilyPlanDTO> dto = new PageInfoDTO<>();
        BeanUtils.copyProperties(info, dto);
        dto.setList(info.getList().stream()
                .map(tbPlan -> {
                    FamilyPlanDTO familyPlanDTO = new FamilyPlanDTO();
                    BeanUtils.copyProperties(tbPlan, familyPlanDTO);
                    for (TbUser user : users) {
                        if (tbPlan.getUserId().equals(user.getId())) {
                            familyPlanDTO.setUsername(user.getUsername());
                            break;
                        }
                    }
                    return familyPlanDTO;
                }).collect(Collectors.toList()));
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "可以关联的计划列表", dto);
    }

    /**
     * 获取已登陆的 user 的 id
     *
     * @return {@link Long}
     */
    private Long getUserId() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getIdByName(name);
    }

    /**
     * 获取已登陆的 user
     *
     * @return {@link Long}
     */
    private TbUser getUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.get(name);
    }
}
