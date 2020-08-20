package com.insurance.policy.pay.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/8/19 22:07
 */
@Configuration
public class RabbitMqConfiguration {

    //定义一个dead letter Queue的对象，并给队列一个名字：springboot_dead_letter_queue
    @Bean
    public Queue deadLetterQueue() {
        return new Queue("createMain");
    }
}
