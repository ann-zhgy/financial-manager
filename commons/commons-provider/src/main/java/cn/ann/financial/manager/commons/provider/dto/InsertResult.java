package cn.ann.financial.manager.commons.provider.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Description： <p>mapper 的 insert 方法的返回值传输对象</p>
 *
 * Date: 2020-4-20 21:26
 * @author 88475
 * @version v1.0
 */
@Data
public class InsertResult<T> implements Serializable {
    private static final long serialVersionUID = -8182847778574916240L;

    private T t;
    private int modifyCount;
}
