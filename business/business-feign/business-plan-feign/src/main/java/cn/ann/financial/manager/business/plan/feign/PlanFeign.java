package cn.ann.financial.manager.business.plan.feign;

import cn.ann.financial.manager.business.plan.feign.fallback.PlanFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-4-5:13:36
 */
@FeignClient(value = "business-plan", path = "plan", configuration = cn.ann.financial.manager.configuration.FeignRequestConfiguration.class, fallback = PlanFeignFallback.class)
public interface PlanFeign {
    @GetMapping(value = "available/{userId}")
    @PreAuthorize("hasAuthority('PUBLIC')")
    public String getAvailablePlans(@PathVariable Long userId);

}
