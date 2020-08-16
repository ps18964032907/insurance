package com.insurance.policy.message.controller;


import com.insurance.policy.message.fegin.PolicyMessageCenterFegin;
import com.insurance.policy.message.mail.MailUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.applet.Main;

import java.math.BigDecimal;

/**
 * @Author sjh
 * @creat 2020/8/13 16:08
 */
@RestController
@RequestMapping("/MessageCenter")
public class MessageCenterController {
    private String FINANCE_EXCHANGE="finance_exchange";
    private String POLICY_MAIN_EXCHANGE="policy_main_exchange";
    public String FINANCE_ROUTING_KEY="finance_routing_key";
    public String POLICY_MAIN_ROUTING_KEY="policy_main_routing_key";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MailUtil mailUtil;

    @RequestMapping("/SendToFinance")
    public void sendFinanceMessage(BigDecimal duePremium,long id, String email){
        rabbitTemplate.convertAndSend(FINANCE_EXCHANGE,FINANCE_ROUTING_KEY,id);
        mailUtil.sendFinaceEmail(duePremium,id,email);
        System.out.println("已经发送财务微服务");
    }
    @RequestMapping("/SendToPolicyMain")
    public void sendPolicyMainMessage(long id,String email){
        rabbitTemplate.convertAndSend(POLICY_MAIN_EXCHANGE,POLICY_MAIN_ROUTING_KEY,id);
        mailUtil.sendPolicyEmail(id,email);
        System.out.println("已经发送保单微服务");
    }
}
