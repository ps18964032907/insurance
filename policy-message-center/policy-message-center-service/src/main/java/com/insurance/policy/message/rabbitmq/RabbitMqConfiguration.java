package com.insurance.policy.message.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfiguration {
    private String FINANCE_QUEUE="finance_queue";
    private String POLICY_MAIN_QUEUE="policy_main_queue";
    private String FINANCE_DEAD_LETTER_QUEUE="finance_dead_letter_queue";
    private String POLICY_MAIN_DEAD_LETTER_QUEUE="policy_main_dead_letter_queue";
    private String FINANCE_DEAD_LETTER_EXCHANGE="finance_dead_letter_exchange";
    private String POLICY_MAIN_DEAD_LETTER_EXCHANGE="policy_main_dead_letter_exchange";


    private String FINANCE_EXCHANGE="finance_exchange";
    private String POLICY_MAIN_EXCHANGE="policy_main_exchange";
    public String FINANCE_ROUTING_KEY="finance_routing_key";
    public String POLICY_MAIN_ROUTING_KEY="policy_main_routing_key";

    public String FINANCE_DEAD_LETTER_ROUTING_KEY="finance_dead_letter_routing_key";
    public String POLICY_MAIN_DEAD_LETTER_ROUTING_KEY="policy_main_dead_letter_routing_key";


    //定义一个dead letter Queue的对象
    @Bean("financeDeadLetterQueue")
    public Queue financeDeadLetterQueue(){
        return new Queue(FINANCE_DEAD_LETTER_QUEUE);
    }
    @Bean("policyMainDeadLetterQueue")
    public Queue policyMainDeadLetterQueue(){
        return new Queue(POLICY_MAIN_DEAD_LETTER_QUEUE);
    }


    //定义一个dead letter 交换机
    @Bean("financeDeadLetterExchange")
    public DirectExchange financeDeadLetterExchange(){
        return new DirectExchange(FINANCE_DEAD_LETTER_EXCHANGE);
    }
    @Bean("policyMainDeadLetterExchange")
    public DirectExchange policyMainDeadLetterExchange(){
        return new DirectExchange(POLICY_MAIN_DEAD_LETTER_EXCHANGE);
    }


    //定义一个delay 交换机
    @Bean("financeExchange")
    public DirectExchange financeExchange(){
        return new DirectExchange(FINANCE_EXCHANGE);
    }
    @Bean("policyMainExchange")
    public DirectExchange policyMainExchange(){
        return new DirectExchange(POLICY_MAIN_EXCHANGE);
    }



    //定义延迟队列
    @Bean("financeQueue")
    public Queue delayQueue(){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl",5000);
        arguments.put("x-dead-letter-exchange",FINANCE_DEAD_LETTER_EXCHANGE);
        arguments.put("x-dead-letter-routing-key",FINANCE_DEAD_LETTER_ROUTING_KEY);

        Queue queue = QueueBuilder.durable(FINANCE_QUEUE).withArguments(arguments).build();
        return queue;
    }

    @Bean("policyMainQueue")
    public Queue policyMainQueue(){
        Map<String,Object> arguments1 = new HashMap<>();
        arguments1.put("x-message-ttl",5000);
        arguments1.put("x-dead-letter-exchange",POLICY_MAIN_DEAD_LETTER_EXCHANGE);
        arguments1.put("x-dead-letter-routing-key",POLICY_MAIN_DEAD_LETTER_ROUTING_KEY);

        Queue queue1 = QueueBuilder.durable(POLICY_MAIN_QUEUE).withArguments(arguments1).build();
        return queue1;
    }


    //绑定延迟队列和延迟交换机
    @Bean
    public Binding financeQueueBinding(@Qualifier("financeQueue") Queue queue,
                                     @Qualifier("financeExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(FINANCE_ROUTING_KEY);
    }

    @Bean
    public Binding policyMainQueueBinding(@Qualifier("policyMainQueue") Queue queue,
                                     @Qualifier("policyMainExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(POLICY_MAIN_ROUTING_KEY);
    }

    //绑定死信队列和死信交换机
    @Bean
    public Binding financeDeadLetterQueueBinding(@Qualifier("financeDeadLetterQueue") Queue queue,
                                          @Qualifier("financeDeadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(FINANCE_DEAD_LETTER_ROUTING_KEY);
    }


    @Bean
    public Binding policyMainDeadLetterQueueBinding(@Qualifier("policyMainDeadLetterQueue") Queue queue,
                                          @Qualifier("policyMainDeadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(POLICY_MAIN_DEAD_LETTER_ROUTING_KEY);
    }
}
