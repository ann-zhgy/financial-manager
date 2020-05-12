package cn.ann.financial.manager.provider.user.service;

import cn.ann.financial.manager.provider.user.api.TbUserRoleServiceApi;
import cn.ann.financial.manager.provider.user.domain.TbUserRole;
import cn.ann.financial.manager.provider.user.mapper.TbUserRoleMapper;
import com.sun.istack.internal.NotNull;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

/**
 * Description：
 * 
 * Date: 2020-5-4 16:41
 * @author 88475
 * @version v1.0
 */
@Service(version = "1.0.0")
public class TbUserRoleService implements TbUserRoleServiceApi {
    @Resource
    private TbUserRoleMapper tbUserRoleMapper;


    @Override
    public TbUserRole get(@NotNull Long id) {
        return tbUserRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long get(@NotNull Long userId, @NotNull Long roleId) {
        TbUserRole userRole = new TbUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return tbUserRoleMapper.selectOne(userRole).getId();
    }

    @Override
    public int insert(@NotNull TbUserRole userRole) {
        userRole.setId(null);
        return tbUserRoleMapper.insert(userRole);
    }

    @Override
    public int update(@NotNull TbUserRole userRole) {
        TbUserRole oldUserRole = tbUserRoleMapper.selectByPrimaryKey(userRole.getId());
        BeanUtils.copyProperties(userRole, oldUserRole);

        return tbUserRoleMapper.updateByPrimaryKey(oldUserRole);
    }

    @Override
    public int delete(@NotNull Long id) {
        return tbUserRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(@NotNull Long userId, @NotNull Long roleId) {
        Long id = get(userId, roleId);
        return tbUserRoleMapper.deleteByPrimaryKey(id);
    }
}
