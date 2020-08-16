package com.insurance.policy.admin.consumer;

//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/8/15 16:11
 */
@Component
public class AdminConsumer {

//    @RabbitListener(queues = "item_queue")
    public void msg(String msg){
        System.out.println("消费者消费消息了："+msg);
        //TODO 这里可以做异步的工作

    }


}
