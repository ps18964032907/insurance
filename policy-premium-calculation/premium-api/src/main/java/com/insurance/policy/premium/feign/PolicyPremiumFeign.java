package com.insurance.policy.premium.feign;

import com.insurance.policy.admin.domain.ComBinedPolicy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Author mhd
 *@Description //保费计算
 */
@FeignClient("policy-premium-calculation")
public interface PolicyPremiumFeign {

/**
 * @Description 传comBinedPolicy对象计算保费返回comBinedPolicy对象
 * @param comBinedPolicy
 * @return com.insurance.policy.admin.domain.ComBinedPolicy
 * @author mhd
 * @date 2020/8/13
 */
@RequestMapping("/premium/calculatePolicy")
 ComBinedPolicy calculatePolicy(ComBinedPolicy comBinedPolicy);
}
