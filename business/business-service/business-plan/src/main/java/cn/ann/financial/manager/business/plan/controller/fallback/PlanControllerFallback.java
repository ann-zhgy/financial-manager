package cn.ann.financial.manager.business.plan.controller.fallback;

import cn.ann.financial.manager.business.plan.dto.TbPlanDTO;
import cn.ann.financial.manager.business.plan.dto.param.ModifyPlan;
import cn.ann.financial.manager.business.plan.dto.param.ModifyStatus;
import cn.ann.financial.manager.commons.constant.FallbackConstant;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.provider.dto.param.ModifyOpen;
import cn.ann.financial.manager.provider.plan.domain.TbPlan;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-25:1:44
 */
@Slf4j
public class PlanControllerFallback {
    public ResponseResult<Void> saveFallback(TbPlan plan, Throwable ex) {
        log.warn("Invoke saveFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<List<TbPlanDTO>> getPlansFallback(Throwable ex) {
        log.warn("Invoke getPlansFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<TbPlan> getPlanFallback(Long id, Throwable ex) {
        log.warn("Invoke getPlanFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> updatePlanFallback(ModifyPlan param, Throwable ex) {
        log.warn("Invoke updatePlanFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> updateStatusFallback(ModifyStatus param, Throwable ex) {
        log.warn("Invoke updateStatusFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> updateOpenFallback(ModifyOpen param, Throwable ex) {
        log.warn("Invoke updateOpenFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<List<TbPlanDTO>> getAvailablePlansFallback(Throwable ex) {
        log.warn("Invoke getAvailablePlansFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<List<TbPlanDTO>> getMemberPlansFallback(int pageNum, Throwable ex) {
        log.warn("Invoke getMemberPlansFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }
}
