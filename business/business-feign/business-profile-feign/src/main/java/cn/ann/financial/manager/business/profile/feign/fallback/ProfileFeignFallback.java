package cn.ann.financial.manager.business.profile.feign.fallback;

import cn.ann.financial.manager.business.profile.dto.param.IconParam;
import cn.ann.financial.manager.business.profile.dto.param.PasswordParam;
import cn.ann.financial.manager.business.profile.dto.param.ProfileParam;
import cn.ann.financial.manager.business.profile.feign.ProfileFeign;
import cn.ann.financial.manager.commons.constant.FallbackConstant;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:14:10
 */
@Component
public class ProfileFeignFallback implements ProfileFeign {
    @Override
    public String info(String username) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String update(ProfileParam profileParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyPassword(PasswordParam passwordParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyIcon(IconParam iconParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
