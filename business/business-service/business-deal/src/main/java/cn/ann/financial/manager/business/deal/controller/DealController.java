package cn.ann.financial.manager.business.deal.controller;

import cn.ann.financial.manager.business.deal.dto.DealDataDTO;
import cn.ann.financial.manager.business.deal.dto.FamilyDealDTO;
import cn.ann.financial.manager.business.deal.dto.TbDealDTO;
import cn.ann.financial.manager.business.deal.dto.param.DealsParam;
import cn.ann.financial.manager.business.deal.dto.param.ModifyDeal;
import cn.ann.financial.manager.business.deal.dto.param.SaveDeal;
import cn.ann.financial.manager.commons.constant.PageConstant;
import cn.ann.financial.manager.commons.constant.ProviderConstant;
import cn.ann.financial.manager.commons.dto.PageInfoDTO;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import cn.ann.financial.manager.commons.provider.dto.param.ModifyOpen;
import cn.ann.financial.manager.provider.deal.api.TbDealServiceApi;
import cn.ann.financial.manager.provider.deal.domain.TbDeal;
import cn.ann.financial.manager.provider.deal.param.DealCondition;
import cn.ann.financial.manager.provider.plan.api.TbPlanServiceApi;
import cn.ann.financial.manager.provider.plan.domain.TbPlan;
import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-4-5:8:49
 */
@RestController
@RequestMapping("deal")
public class DealController {
    @Reference(version = "1.0.0")
    private TbDealServiceApi dealService;

    @Reference(version = "1.0.0")
    private TbPlanServiceApi planService;

    @Reference(version = "1.0.0")
    private TbUserServiceApi userService;

    /**
     * 保存交易信息
     *
     * @param param {@link SaveDeal}
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping("save")
    public ResponseResult<Void> save(@RequestBody SaveDeal param) {
        TbDeal deal = new TbDeal();
        BeanUtils.copyProperties(param, deal);
        deal.setUserId(this.getUserId());
        InsertResult<TbDeal> result = dealService.insert(deal);
        if (deal.getPlanId() != null) {
            TbPlan plan = new TbPlan();
            plan.setId(deal.getPlanId());
            plan.setDealId(result.getT().getId());
            plan.setStatus(ProviderConstant.PLAN_FINISH);
            int result1 = planService.updateStatus(plan);
            if (result1 <= 0) {
                throw new RuntimeException("交易记录已关联计划，但是修改计划状态失败");
            }
        }
        if (result.getModifyCount() > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "创建交易记录成功");
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "创建交易记录失败");
        }
    }

    /**
     * 根据 id 获取交易记录
     *
     * @param id id
     * @return {@link ResponseResult<TbDealDTO>}
     */
    @GetMapping("/{id}")
    public ResponseResult<TbDealDTO> getDeal(@PathVariable Long id) {
        TbDeal deal = dealService.get(id);
        TbDealDTO dto = new TbDealDTO();
        BeanUtils.copyProperties(deal, dto);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取交易记录成功", dto);
    }

    /**
     * 获取该用户下所有的交易信息
     *
     * @return 交易列表
     */
    @PostMapping("list")
    public ResponseResult<List<TbDealDTO>> list(@RequestBody(required = false) DealCondition condition) {
        if (condition == null) {
            condition = new DealCondition();
        }
        condition.setUserId(this.getUserId());
        List<TbDealDTO> dtos = dealService.get(condition).stream()
                .map(tbDealGroup -> {
                    TbDealDTO dto = new TbDealDTO();
                    BeanUtils.copyProperties(tbDealGroup, dto);
                    return dto;
                }).collect(Collectors.toList());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取交易列表成功", dtos);
    }

    /**
     * 根据 条件 获取交易记录 分页
     *
     * @return {@link ResponseResult<TbDealDTO>}
     */
    @PostMapping("list/page")
    public ResponseResult<PageInfoDTO<TbDealDTO>> list(@RequestBody @Valid DealsParam param) {
        if (param.getCondition() == null) {
            param.setCondition(new DealCondition());
        }
        param.getCondition().setUserId(this.getUserId());
        PageInfo<TbDeal> pageInfo = dealService.get(
                param.getCondition(), param.getPageNum(),
                param.getPageCount() == null ? PageConstant.PAGE_SIZE : param.getPageCount());
        PageInfoDTO<TbDealDTO> dto = new PageInfoDTO<>();
        BeanUtils.copyProperties(pageInfo, dto);
        dto.setList(pageInfo.getList().stream()
                .map(tbDeal -> {
                    TbDealDTO dealDTO = new TbDealDTO();
                    BeanUtils.copyProperties(tbDeal, dealDTO);
                    return dealDTO;
                }).collect(Collectors.toList()));
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取交易记录分页成功", dto);
    }

    /**
     * 获取交易组下的交易信息
     *
     * @param groupId 交易组 id
     * @return 交易列表
     */
    @GetMapping("list/group/{groupId}")
    public ResponseResult<List<TbDealDTO>> listByGroup(@PathVariable Long groupId) {
        TbDeal param = new TbDeal();
        param.setDealGroup(groupId);
        List<TbDealDTO> dtos = dealService.get(param).stream()
                .map(tbDealGroup -> {
                    TbDealDTO dto = new TbDealDTO();
                    BeanUtils.copyProperties(tbDealGroup, dto);
                    return dto;
                }).collect(Collectors.toList());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取交易组下的交易列表成功", dtos);
    }


    /**
     * 获取 交易数据。
     *
     * @return {@link ResponseResult<List<TbDealDTO>>}
     */
    @GetMapping(value = "data")
    public ResponseResult<List<DealDataDTO>> getDealData(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        DealCondition condition = new DealCondition();
        condition.setBeginTime(beginTime);
        condition.setEndTime(endTime == null ? new Date() : endTime);
        List<DealDataDTO> dtos = dealService.get(condition)
                .stream()
                .map(deal -> {
                    DealDataDTO dto = new DealDataDTO();
                    BeanUtils.copyProperties(deal, dto);
                    return dto;
                }).collect(Collectors.toList());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取交易列表成功", dtos);
    }


    /**
     * 修改交易信息
     *
     * @param param 交易信息
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "modify")
    public ResponseResult<Void> update(@RequestBody ModifyDeal param) {
        if (param.getPlanId() != null) {
            TbPlan plan = planService.get(param.getPlanId());

            plan.setMoney(param.getMoney());
            plan.setType(param.getType());
            planService.update(plan);
        }
        TbDeal deal = new TbDeal();
        BeanUtils.copyProperties(param, deal);
        int result = dealService.update(deal);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新交易信息成功");
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新交易信息失败");
    }

    /**
     * 设置 交易信息 是否公开
     *
     * @param param {@link ModifyOpen}
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "open")
    public ResponseResult<Void> updateOpen(@RequestBody ModifyOpen param) {
        TbDeal deal = new TbDeal();
        deal.setId(param.getId());
        deal.setOpen(param.getOpen());
        int result = dealService.updateOpen(deal);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改交易信息公开状态成功");
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改交易信息公开状态失败");
    }

    /**
     * 删除 交易信息
     *
     * @param dealId 交易信息 id
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "delete")
    public ResponseResult<Void> deleteOpen(@RequestBody Long dealId) {
        int result = dealService.delete(dealId);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改交易信息公开状态成功");
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改交易信息公开状态失败");
    }

    /**
     * 获取 家庭成员 公开的交易信息。
     *
     * @return {@link ResponseResult<List<TbDealDTO>>}
     */
    @GetMapping(value = "family/{pageNum}")
    public ResponseResult<PageInfoDTO<FamilyDealDTO>> getMemberDeals(@PathVariable int pageNum) {
        List<Long> ids = userService.getIdsByFamilyId(this.getUser().getFamilyId());
        List<TbUser> users = ids.stream().map(id -> userService.get(id)).collect(Collectors.toList());
        PageInfo<TbDeal> info = dealService.get(ids, pageNum, 10);
        PageInfoDTO<FamilyDealDTO> dto = new PageInfoDTO<>();
        BeanUtils.copyProperties(info, dto);
        dto.setList(info.getList().stream()
                .map(deal -> {
                    FamilyDealDTO familyDealDTO = new FamilyDealDTO();
                    BeanUtils.copyProperties(deal, familyDealDTO);
                    for (TbUser user : users) {
                        if (deal.getUserId().equals(user.getId())) {
                            familyDealDTO.setUsername(user.getUsername());
                            break;
                        }
                    }
                    return familyDealDTO;
                }).collect(Collectors.toList()));
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取家庭所有的交易信息成功", dto);
    }

    /**
     * 获取 家庭 交易数据。
     *
     * @return {@link ResponseResult<List<TbDealDTO>>}
     */
    @GetMapping(value = "family/data")
    public ResponseResult<List<DealDataDTO>> getMemberDealData(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
        List<Long> ids = userService.getIdsByFamilyId(this.getUser().getFamilyId());
        List<DealDataDTO> dtos = dealService.get(ids, beginTime, endTime == null ? new Date() : endTime)
                .stream()
                .map(deal -> {
                    DealDataDTO dto = new DealDataDTO();
                    BeanUtils.copyProperties(deal, dto);
                    return dto;
                }).collect(Collectors.toList());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "可以关联的计划列表", dtos);
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
