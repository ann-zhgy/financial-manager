package cn.ann.financial.manager.provider.user.mapper;

import cn.ann.financial.manager.provider.user.domain.TbPermission;
import cn.ann.financial.manager.provider.user.domain.TbRole;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import cn.ann.financial.manager.provider.user.domain.TbUserRole;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.TkMapper;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-21:19:16
 */
public interface TbUserMapper extends TkMapper<TbUser> {
    Long getIdByName(String name);

    List<String> getUrls(String username);

    List<Long> getIdsByFamilyId(Long familyId);

    List<TbPermission> getMenu(Long userId);

    List<TbRole> getRoles(Long userId);
}