package com.insurance.policy.message.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author sjh
 * @creat 2020/8/14 10:19
 */
@FeignClient(value = "policy-message-center-service",path = "/MessageCenter")
public interface PolicyMessageCenterFegin {
    @RequestMapping("/SendToFinance/{id}")
    long sendFinanceMessage(@PathVariable("id")long id);

    @RequestMapping("/SendToPolicyMain/{id}")
    long sendPolicyMainMessage(@PathVariable("id")long id);
}
