package com.insurance.policy.pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author jiangshuai
 * @date 2020/8/13 0013 14:24
 */
@RestController
@RefreshScope//增加这个注解可以接受动态刷新配置文件的参数
@RequestMapping("/pay")
public class PayController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/payMoney")
    public void payMoney() {
        String object = restTemplate.getForObject("http://policy-pay-service/pay/payMoney", String.class);

    }

}
