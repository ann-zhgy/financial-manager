package cn.ann.financial.manager.business.plan.dto.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ModifyPlan implements Serializable {
    private static final long serialVersionUID = -7365042864855085895L;

    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private Double money;

    private Integer type;

    private String intro;

    private String remark;

    private Long dealId;

    private Integer open;
}
