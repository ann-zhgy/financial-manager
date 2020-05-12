package cn.ann.financial.manager.provider.deal.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Description：交易信息查询条件
 * <p>
 * Date: 2020-5-9 9:00
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class DealCondition implements Serializable {
    private static final long serialVersionUID = 2605265029170411013L;

    /** 简介 */
    private String intro;

    /** 最小金额 */
    private Double minMoney;

    /** 最大金额 */
    private Double maxMoney;

    /** 类型：收入 | 支出 */
    private Integer type;

    /** 交易开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    /** 交易结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /** 备注 */
    private String remark;

    /** 公开 */
    private Integer open;

    /** 创建者ID */
    private Long userId;

    /** 收支组 */
    private Long dealGroup;
}
