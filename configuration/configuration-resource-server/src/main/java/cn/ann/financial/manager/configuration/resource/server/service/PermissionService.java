package cn.ann.financial.manager.configuration.resource.server.service;

import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description：权限验证服务
 * <p>
 * Date: 2020-5-4 12:43
 *
 * @author 88475
 * @version v1.0
 */
@Slf4j
@Component
public class PermissionService {
    @Reference(version = "1.0.0")
    private TbUserServiceApi userService;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public boolean hasAuthentication(HttpServletRequest request, Authentication authentication) {
        String username = authentication.getName();
        String requestURI = request.getRequestURI();
        log.info("==================================================================");
        log.info("当前登录用户为：" + username + ", 开始权限验证, 验证 url 为：" + requestURI);
        List<String> urls = userService.getUrls(username);
        boolean result = urls.stream().anyMatch(url -> pathMatcher.match(url, requestURI));
        log.info(result ? "当前登录用户拥有该权限" : "当前登录用户没有该权限");
        return result;
    }

}
