package cn.ann.financial.manager.provider.deal.group.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：交易组 查询参数
 * <p>
 * Date: 2020-5-9 20:03
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class DealGroupCondition implements Serializable {
    private static final long serialVersionUID = -2604523805015093737L;

    /** 名字 */
    private String name;

    /** 简介 */
    private String intro;

    /** 公开 */
    private Integer open;

    /** 创建者ID */
    private Long userId;

}
