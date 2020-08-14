package com.insurance.policy.message.rabbitmq;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqDelayListener {
    private static final String DEAD_LETTER_QUEUE="springboot_dead_letter_queue";
    @RabbitListener(queues = DEAD_LETTER_QUEUE)
    public void recv( String myMsg){
        System.out.println("收到死信队列消息："+myMsg);
    }
}
