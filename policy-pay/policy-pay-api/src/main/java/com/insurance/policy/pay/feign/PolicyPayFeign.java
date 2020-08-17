package com.insurance.policy.pay.feign;

import com.insurance.policy.pay.controller.VehiclePayController;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jiangshuai
 * @date 2020/8/13 0013 12:04
 */
@FeignClient(value = "policy-pay-service", fallback = VehiclePayController.class)
public interface PolicyPayFeign {

    @RequestMapping("/pay/payMoney/{id}")
    void payMoney();

    @RequestMapping("/notify/{id}")
    void doNotify(@PathVariable("id") long id);

    @RequestMapping("/return/{id}")
    String doReturn(@PathVariable("id") long id);
}
