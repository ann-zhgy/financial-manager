package cn.ann.financial.manager.business.oauth2.controller.fallback;

import cn.ann.financial.manager.business.oauth2.dto.param.LoginParameter;
import cn.ann.financial.manager.commons.constant.FallbackConstant;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-23:17:20
 */
@Slf4j
public class UserControllerFallback {
    public ResponseResult<Void> loginFallback(LoginParameter parameter, Throwable ex) {
        log.warn("Invoke loginFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> infoFallback(String token, Throwable ex) {
        log.warn("Invoke infoFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> resetAuthentication(Throwable ex) {
        log.warn("Invoke infoFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> idFallback(Throwable ex) {
        log.warn("Invoke infoFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> logoutFallback(String accessToken, Throwable ex) {
        log.warn("Invoke logoutFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }
}
