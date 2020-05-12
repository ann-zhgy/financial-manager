package cn.ann.financial.manager.provider.user.api;

import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import cn.ann.financial.manager.provider.user.domain.TbPermission;
import cn.ann.financial.manager.provider.user.domain.TbRole;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import cn.ann.financial.manager.provider.user.param.UpdateEnableParam;
import cn.ann.financial.manager.provider.user.param.UpdateFamilyParam;
import cn.ann.financial.manager.provider.user.param.UpdateOpenParam;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-20:19:16 
 */
public interface TbUserServiceApi {
    TbUser get(@NotNull String name);

    TbUser get(@NotNull Long id);

    TbUser getByEmail(@NotNull String email);

    Long getIdByName(@NotNull String name);

    List<TbUser> getListByFamilyId(@NotNull Long familyId);

    List<Long> getIdsByFamilyId(@NotNull Long familyId);

    List<String> getUrls(@NotNull String username);

    List<TbPermission> getMenu(@NotNull Long userId);

    List<TbRole> getRoles(@NotNull Long userId);

    InsertResult<TbUser> insert(@NotNull TbUser user);

    int update(@NotNull TbUser user);

    int updateFamily(@NotNull UpdateFamilyParam param);

    int updateOpen(@NotNull UpdateOpenParam param);

    int updateEnable(@NotNull UpdateEnableParam param);

    int delete(@NotNull String username);

    int modifyPassword(@NotNull String username, String newPassword);

    int modifyIcon(@NotNull String username, String path);
}
