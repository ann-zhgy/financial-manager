package cn.ann.financial.manager.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description：分页数据传输对象
 * <p>
 * Date: 2020-4-21 18:00
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class PageInfoDTO<T> implements Serializable {
    private static final long serialVersionUID = -4545146383948671811L;

    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //总记录数
    protected long total;
    //结果集
    protected List<T> list;

}
