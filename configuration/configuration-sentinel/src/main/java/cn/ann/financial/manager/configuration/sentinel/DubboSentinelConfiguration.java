package cn.ann.financial.manager.configuration.sentinel;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.alibaba.csp.sentinel.slots.block.SentinelRpcException;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Dubbo Sentinel AOP
 * <p>
 * Description: 仅 Dubbo 服务需要该配置，Spring Cloud Alibaba 无需加载该配置
 * </p>
 */
@Configuration
public class DubboSentinelConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DubboFallbackRegistry.class);
    @PostConstruct
    public void registerDubboFallback() {
        DubboFallbackRegistry.setProviderFallback((invoker, invocation, ex) -> {
            logger.warn("捕获到block异常，降级处理: " + ex.getClass().getTypeName());
            throw new SentinelRpcException(ex);
        });
    }

}
