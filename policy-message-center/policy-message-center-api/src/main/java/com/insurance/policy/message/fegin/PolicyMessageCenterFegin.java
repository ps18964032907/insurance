package com.insurance.policy.message.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

/**
 * @Author sjh
 * @creat 2020/8/14 10:19
 */
@FeignClient(value = "policy-message-center-service",path = "/MessageCenter")
public interface PolicyMessageCenterFegin {
    @RequestMapping("/SendToFinance")
    long sendFinanceMessage(BigDecimal duePremium, long id, String email);

    @RequestMapping("/SendToPolicyMain")
    long sendPolicyMainMessage(long id,String email);
}
