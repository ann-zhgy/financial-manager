package cn.ann.financial.manager.business.deal.dto.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：请求 data 的参数
 * <p>
 * Date: 2020-5-12 0:54
 *
 * @author 88475
 * @version v1.0
 */
@Data
public class DataParam implements Serializable {
    private static final long serialVersionUID = 3896463377595851570L;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
