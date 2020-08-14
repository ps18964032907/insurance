package com.insurance.policy.message.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/SendToFinance/{id}")
    public long sendFinanceMessage(@PathVariable("id")long id){
        rabbitTemplate.convertAndSend(FINANCE_EXCHANGE,FINANCE_ROUTING_KEY,id);
        System.out.println("已经发送财务微服务");
        return id;
    }
    @RequestMapping("/SendToPolicyMain/{id}")
    public long sendPolicyMainMessage(@PathVariable("id")long id){
        rabbitTemplate.convertAndSend(POLICY_MAIN_EXCHANGE,POLICY_MAIN_ROUTING_KEY,id);
        System.out.println("已经发送保单微服务");
        return id;
    }
}
