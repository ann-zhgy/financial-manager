package cn.ann.financial.manager.commons.provider.service.impl;

import cn.ann.financial.manager.commons.constant.ProviderConstant;
import cn.ann.financial.manager.commons.provider.domain.BaseDomain;
import cn.ann.financial.manager.commons.provider.dto.InsertResult;
import cn.ann.financial.manager.commons.provider.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.TkMapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:20:46
 */
public abstract class BaseServiceImpl<T extends BaseDomain, D extends TkMapper<T>> implements BaseService<T> {
    @Resource
    protected D mapper;

    /**
     * 通过 id 获取对象
     *
     * @param id id
     * @return {@link T}
     */
    @Override
    public final T get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 通过 对象的某个或某几个属性获取对象
     * 多个属性时生成的 where 语句使用 and 连接
     *
     * @param t {@link T}
     * @return 对象的集合 {@link T}
     */
    @Override
    public final List<T> get(T t) {
        Example example = new Example(t.getClass());
        example.orderBy("created").desc();
        example.createCriteria()
                .andEqualTo(t);
        return mapper.selectByExample(example);
    }

    /**
     * 通过条件查询并分页
     *
     * @param t  {@link T}
     * @param currPage 当前页
     * @param count    每页条数
     * @return {@link PageInfo<T>}
     */
    @Override
    public final PageInfo<T> get(T t, Integer currPage, Integer count) {
        PageHelper.startPage(currPage, count);
        Example example = new Example(t.getClass());
        example.orderBy("created").desc();
        example.createCriteria().andEqualTo(t);
        List<T> list = mapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 向数据库中插入数据。插入数据之前会初始化 创建时间（created） 和 更新时间（updated） 字段，
     * 如果 open 没有设置，默认设置为 {@link ProviderConstant} 的 NO_OPEN
     *
     * @param t {@link T}
     * @return 数据库记录增加条数
     */
    @Override
    public final InsertResult<T> insert(T t) {
        baseInsertInit(t);
        insertInit(t);
        int insertNum = mapper.insert(t);
        InsertResult<T> result = new InsertResult<>();
        result.setModifyCount(insertNum);
        result.setT(t);
        return result;
    }

    @Override
    public final int update(T t) {
        T oldT = get(t.getId());
        baseUpdateInit(oldT, t);
        updateInit(oldT, t);
        return mapper.updateByPrimaryKey(oldT);
    }

    /**
     * 更新对象是否是否公开
     *
     * @param t {@link T}
     * @return 数据库记录更新条数
     */
    @Override
    public final int updateOpen(T t) {
        T oldT = get(t.getId());
        oldT.setOpen(t.getOpen());
        oldT.setUpdated(new Date());
        return mapper.updateByPrimaryKey(oldT);
    }

    /**
     * 根据传入的 id 删除数据库中的数据
     *
     * @param id id
     * @return 数据库记录删除条数
     */
    @Override
    public final int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 对象初始化方法，将会在插入数据前执行，已经默认初始化 创建时间（created） 和 更新时间（updated） 字段，
     * 如果 open 没有设置，默认设置为 {@link ProviderConstant} 的 NO_OPEN
     *
     * @param t {@link T}
     */
    protected abstract void insertInit(T t);

    /**
     * 对象初始化方法，将会在更新数据前执行，已经默认初始化 更新时间（updated） 字段，
     * 如果 open 没有设置，则不更新（保持为原来的值），如果 open 有值，更新
     *
     * @param oldT 原来的对象，将会更新到数据库中 {@link T}
     * @param t 传进来的对象 {@link T}
     */
    protected abstract void updateInit(T oldT, T t);

    private void baseInsertInit(T t) {
        t.setId(null);
        t.setCreated(new Date());
        t.setUpdated(new Date());
        if (t.getOpen() == null
                || (t.getOpen() != ProviderConstant.OPEN
                && t.getOpen() != ProviderConstant.NO_OPEN)) {
            t.setOpen(ProviderConstant.NO_OPEN);
        }
    }

    private void baseUpdateInit(T oldT, T t) {
        oldT.setUpdated(new Date());
        if (t.getOpen() != null
                && (t.getOpen() == ProviderConstant.OPEN
                || t.getOpen() == ProviderConstant.NO_OPEN)){
            oldT.setOpen(t.getOpen());
        }
    }
}
