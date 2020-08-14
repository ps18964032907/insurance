package com.insurance.policy.pay.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jiangshuai
 * @date 2020/8/13 0013 12:04
 */
@FeignClient("policy-pay-service")
public interface PolicyPayFeign {

    @RequestMapping("/payMoney")
    public void payMoney();

}
