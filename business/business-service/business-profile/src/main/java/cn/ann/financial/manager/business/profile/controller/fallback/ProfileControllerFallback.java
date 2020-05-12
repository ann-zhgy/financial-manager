package cn.ann.financial.manager.business.profile.controller.fallback;

import cn.ann.financial.manager.business.profile.dto.TbPermissionDTO;
import cn.ann.financial.manager.business.profile.dto.TbUserDTO;
import cn.ann.financial.manager.business.profile.dto.param.IconParam;
import cn.ann.financial.manager.business.profile.dto.param.PasswordParam;
import cn.ann.financial.manager.business.profile.dto.param.ProfileParam;
import cn.ann.financial.manager.commons.constant.FallbackConstant;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Description：profileController 熔断类
 * <p>
 * Date: 2020-5-4 23:35
 *
 * @author 88475
 * @version v1.0
 */
@Slf4j
public class ProfileControllerFallback {
    public static ResponseResult<TbUserDTO> infoFallback(Throwable ex) {
        log.warn("Invoke infoFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<List<TbPermissionDTO>> menuFallback(Throwable ex) {
        log.warn("Invoke menuFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> updateFallback(ProfileParam param, Throwable ex) {
        log.warn("Invoke updateFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> modifyPasswordFallback(PasswordParam passwordParam, Throwable ex) {
        log.warn("Invoke modifyPasswordFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> modifyIconFallback(IconParam iconParam, Throwable ex) {
        log.warn("Invoke modifyIconFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }

    public ResponseResult<Void> deleteFallback(Throwable ex) {
        log.warn("Invoke deleteFallback: " + ex.getClass().getTypeName());
        ex.printStackTrace();
        return new ResponseResult<>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE);
    }
}
