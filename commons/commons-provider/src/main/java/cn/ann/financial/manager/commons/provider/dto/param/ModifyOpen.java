package cn.ann.financial.manager.commons.provider.dto.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModifyOpen implements Serializable {
    private static final long serialVersionUID = 5029007100084844311L;

    private Long id;

    private Integer open;
}
