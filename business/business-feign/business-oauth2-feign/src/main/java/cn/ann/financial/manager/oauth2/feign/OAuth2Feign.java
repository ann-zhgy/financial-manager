package cn.ann.financial.manager.oauth2.feign;

import cn.ann.financial.manager.commons.dto.ResponseResult;
import cn.ann.financial.manager.oauth2.feign.fallback.OAuth2FeignFallback;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * Description：oAuth2 开放的api
 * <p>
 * Date: 2020-4-26 14:51
 *
 * @author 88475
 * @version v1.0
 */
@FeignClient(value = "business-oauth2", path = "user", configuration = cn.ann.financial.manager.configuration.FeignRequestConfiguration.class, fallback = OAuth2FeignFallback.class)
public interface OAuth2Feign {
    @PostMapping(value = "logout")
    String logout();
}
