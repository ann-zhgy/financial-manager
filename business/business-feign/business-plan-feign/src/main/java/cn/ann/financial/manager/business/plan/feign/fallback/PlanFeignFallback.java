package cn.ann.financial.manager.business.plan.feign.fallback;

import cn.ann.financial.manager.business.plan.feign.PlanFeign;
import cn.ann.financial.manager.commons.constant.FallbackConstant;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-4-5:13:37
 */
@Component
public class PlanFeignFallback implements PlanFeign {
    @Override
    public String getAvailablePlans(Long userId) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
