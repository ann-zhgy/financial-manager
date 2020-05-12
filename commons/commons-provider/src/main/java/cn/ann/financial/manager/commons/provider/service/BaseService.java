package cn.ann.financial.manager.commons.provider.service;

import cn.ann.financial.manager.commons.provider.domain.BaseDomain;
import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import com.github.pagehelper.PageInfo;
import com.sun.istack.internal.NotNull;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:20:41
 */
public interface BaseService<T extends BaseDomain> {
    T get(@NotNull Long id);

    List<T> get(@NotNull T t);

    PageInfo<T> get(@NotNull T t, @NotNull Integer currPage, @NotNull Integer count);

    InsertResult<T> insert(@NotNull T t);

    int update(@NotNull T t);

    int updateOpen(@NotNull T t);

    int delete(@NotNull Long id);
}
