package cn.ann.financial.manager.business.register.controller.fallback;

import cn.ann.financial.manager.business.register.dto.param.ActiveParam;
import cn.ann.financial.manager.commons.constant.FallbackConstant;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import lombok.extern.slf4j.Slf4j;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:17:28
 */
@Slf4j
public class RegisterControllerFallback {
    public ResponseResult<TbUser> registerFallback(TbUser user, Throwable ex) {
        log.warn("Invoke registerFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<TbUser> checkUsernameFallback(String username, Throwable ex) {
        log.warn("Invoke checkUsernameFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<TbUser> checkEmailFallback(String email, Throwable ex) {
        log.warn("Invoke checkEmailFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<TbUser> activeFallback(ActiveParam param, Throwable ex) {
        log.warn("Invoke activeFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }
}
