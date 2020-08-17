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
        /*
            duePremium                           保费
            id                                 保单id
            email                          用户邮箱地址
            FINANCE_QUEUE                        队列
            FINANCE_DEAD_LETTER_EXCHANGE      死信队列
         */

    @RequestMapping("/SendToFinance")
    public void sendFinanceMessage(long id){
        rabbitTemplate.convertAndSend(FINANCE_EXCHANGE,FINANCE_ROUTING_KEY,id);

        System.out.println("已经发送财务微服务");
    }

    /*
            id                              保单id
            email                         用户邮箱地址
            POLICY_MAIN_QUEUE                队列
            POLICY_MAIN_DEAD_LETTER_QUEUE  死信队列

         */

    @RequestMapping("/SendToPolicyMain")
    public void sendPolicyMainMessage(long id,String email){
        rabbitTemplate.convertAndSend(POLICY_MAIN_EXCHANGE,POLICY_MAIN_ROUTING_KEY,id);
        mailUtil.sendPolicyEmail(id,email);
        System.out.println("已经发送保单微服务");
    }
}
