package cn.ann.financial.manager.business.plan.dto.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModifyStatus implements Serializable {
    private static final long serialVersionUID = 520705189146768470L;
    private Long id;
    private Integer status;
}
