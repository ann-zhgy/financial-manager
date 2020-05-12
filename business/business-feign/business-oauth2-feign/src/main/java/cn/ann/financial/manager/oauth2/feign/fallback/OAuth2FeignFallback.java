package cn.ann.financial.manager.oauth2.feign.fallback;

import cn.ann.financial.manager.commons.constant.FallbackConstant;
import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.commons.utils.MapperUtils;
import cn.ann.financial.manager.oauth2.feign.OAuth2Feign;
import org.springframework.stereotype.Component;

/**
 * Description：oAuth2 feign 的熔断
 * <p>
 * Date: 2020-4-26 15:06
 *
 * @author 88475
 * @version v1.0
 */
@Component
public class OAuth2FeignFallback implements OAuth2Feign {
    @Override
    public String logout() {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, FallbackConstant.BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
