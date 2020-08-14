package com.insurance.policy.message.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author sjh
 * @creat 2020/8/14 10:19
 */
@FeignClient("policy-message-center-service")
public interface PolicyMessageCenterFegin {
    @RequestMapping("/MessageCenter/SendToFinance/{id}")
    void sendFinanceMessage(@PathVariable("id")long id);

    @RequestMapping("/MessageCenter/SendToPolicyMain/{id}")
    void sendPolicyMainMessage(@PathVariable("id")long id);
}
