package cn.ann.financial.manager.provider.user.api;

import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import cn.ann.financial.manager.provider.user.domain.TbUserRole;
import com.sun.istack.internal.NotNull;

/**
 * Description：
 * <p>
 * Date: 2020-5-4 16:41
 *
 * @author 88475
 * @version v1.0
 */
public interface TbUserRoleServiceApi {

    /**
     * 通过 id 获取
     *
     * @param id id
     * @return {@link TbUserRole}
     */
    TbUserRole get(@NotNull Long id);

    /**
     * 通过 user_id 和 role_id 获取
     *
     * @param userId user_id
     * @param roleId role_id
     * @return {@link TbUserRole}
     */
    Long get(@NotNull Long userId, @NotNull Long roleId);

    /**
     * 增加
     *
     * @param userRole {@link TbUserRole}
     * @return {@link InsertResult<TbUserRole>}
     */
    int insert(@NotNull TbUserRole userRole);

    /**
     * 更新
     *
     * @param userRole {@link TbUserRole}
     * @return 数据库记录改变数
     */
    int update(@NotNull TbUserRole userRole);

    /**
     * 通过 id 删除
     *
     * @param id id
     * @return 数据库记录改变数
     */
    int delete(@NotNull Long id);

    /**
     * 删除
     *
     * @param userId user id
     * @param roleId role id
     * @return 数据库记录改变数
     */
    int delete(@NotNull Long userId, @NotNull Long roleId);

}
