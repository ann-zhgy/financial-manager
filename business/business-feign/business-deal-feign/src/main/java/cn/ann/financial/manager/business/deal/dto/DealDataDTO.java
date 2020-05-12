package cn.ann.financial.manager.business.deal.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Description：deal 金额 数据传输对象
 * <p>
 * Date: 2020-5-9 10:46
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class DealDataDTO implements Serializable {
    private static final long serialVersionUID = 1481819416884914048L;

    private String intro;

    private int type;

    private double money;
}
