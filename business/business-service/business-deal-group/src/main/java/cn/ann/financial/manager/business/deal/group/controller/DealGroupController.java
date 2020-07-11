package cn.ann.financial.manager.business.deal.group.controller;

import cn.ann.financial.manager.business.deal.group.dto.TbDealGroupDTO;
import cn.ann.financial.manager.business.deal.group.dto.param.DealGroupsParam;
import cn.ann.financial.manager.business.deal.group.dto.param.ModifyDealGroup;
import cn.ann.financial.manager.commons.constant.PageConstant;
import cn.ann.financial.manager.commons.constant.ProviderConstant;
import cn.ann.financial.manager.commons.dto.PageInfoDTO;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import cn.ann.financial.manager.commons.provider.dto.param.ModifyOpen;
import cn.ann.financial.manager.provider.deal.group.api.TbDealGroupServiceApi;
import cn.ann.financial.manager.provider.deal.group.domain.TbDealGroup;
import cn.ann.financial.manager.provider.deal.group.param.DealGroupCondition;
import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import com.github.pagehelper.PageInfo;
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

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:22:15
 */
@RestController
@RequestMapping("deal/group")
public class DealGroupController {
    @Reference(version = "1.0.0")
    private TbDealGroupServiceApi groupService;

    @Reference(version = "1.0.0")
    private TbUserServiceApi userService;

    /**
     * 保存交易组信息
     *
     * @param dealGroup {@link TbDealGroup}
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping("save")
    public ResponseResult<Void> save(@RequestBody TbDealGroup dealGroup) {
        dealGroup.setUserId(this.getUserId());
        InsertResult<TbDealGroup> result = groupService.insert(dealGroup);
        if (result.getModifyCount() > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "添加交易组成功");
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "添加交易组失败");
        }
    }

    /**
     * 获取该用户下所有的交易组
     *
     * @param condition {@link DealGroupCondition}
     * @return {@link ResponseResult<List<TbDealGroupDTO>>}
     */
    @PostMapping("list")
    public ResponseResult<List<TbDealGroupDTO>> list(@RequestBody(required = false) DealGroupCondition condition) {
        if (condition == null) {
            condition = new DealGroupCondition();
        }
        condition.setUserId(this.getUserId());
        List<TbDealGroupDTO> dtos = groupService.get(condition).stream()
                .map(tbDealGroup -> {
                    TbDealGroupDTO dto = new TbDealGroupDTO();
                    BeanUtils.copyProperties(tbDealGroup, dto);
                    return dto;
                }).collect(Collectors.toList());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "查询交易组列表成功", dtos);
    }

    /**
     * 获取该用户下所有的交易组  分页
     *
     * @param param {@link DealGroupsParam}
     * @return {@link ResponseResult<PageInfo<TbDealGroupDTO>>}
     */
    @PostMapping("list/page")
    public ResponseResult<PageInfoDTO<TbDealGroupDTO>> list(@RequestBody @Valid DealGroupsParam param) {
        if (param.getCondition() == null) {
            param.setCondition(new DealGroupCondition());
        }
        param.getCondition().setUserId(this.getUserId());
        PageInfo<TbDealGroup> pageInfo = groupService.get(param.getCondition(), param.getPageNum(),
                param.getPageCount() == null ? PageConstant.PAGE_SIZE : param.getPageCount());
        PageInfoDTO<TbDealGroupDTO> dto = new PageInfoDTO<>();
        BeanUtils.copyProperties(pageInfo, dto);
        dto.setList(pageInfo.getList().stream()
                .map(tbDealGroup -> {
                    TbDealGroupDTO groupDto = new TbDealGroupDTO();
                    BeanUtils.copyProperties(tbDealGroup, groupDto);
                    return groupDto;
                }).collect(Collectors.toList()));
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "查询交易组列表成功", dto);
    }

    /**
     * 通过 id 获取交易组
     *
     * @param id id
     * @return {@link ResponseResult<TbDealGroupDTO>}
     */
    @GetMapping("/{id}")
    public ResponseResult<TbDealGroupDTO> get(@PathVariable Long id) {
        TbDealGroup group = groupService.get(id);
        TbDealGroupDTO dto = new TbDealGroupDTO();
        BeanUtils.copyProperties(group, dto);

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "查询交易组成功", dto);
    }

    /**
     * 修改交易组信息
     *
     * @param param 交易信息
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "modify")
    public ResponseResult<Void> update(@RequestBody ModifyDealGroup param) {
        TbDealGroup group = new TbDealGroup();
        BeanUtils.copyProperties(param, group);
        int result = groupService.update(group);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新交易信息成功");
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "更新交易信息失败");
    }

    /**
     * 设置 交易组信息 是否公开
     *
     * @param param {@link ModifyOpen}
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "open")
    public ResponseResult<Void> updateOpen(@RequestBody ModifyOpen param) {
        TbDealGroup group = new TbDealGroup();
        group.setId(param.getId());
        group.setOpen(param.getOpen());
        int result = groupService.updateOpen(group);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改交易组信息公开状态成功");
        }
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改交易组信息公开状态失败");
    }

    /**
     * 删除 交易组
     *
     * @param id id
     * @return {@link ResponseResult<Void>}
     */
    @PostMapping(value = "delete")
    public ResponseResult<Void> delete(@RequestBody Long id) {
        int result = groupService.delete(id);
        if (result > 0) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改交易组信息公开状态成功");
        } else {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK, "修改交易组信息公开状态失败");
        }
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
