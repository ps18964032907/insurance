package com.insurance.policy.admin.consumer;

//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.insurance.policy.admin.domain.ComBinedPolicy;
import com.insurance.policy.admin.service.PolicyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/8/15 16:11
 */
@Component
public class AdminConsumer {

    @Autowired
    PolicyService policyService;

    /**
     * @Author 潘升
     * @Description //TODO 生成保单
     * @Date 10:12 2020/8/20
     * @Param [msg]
     * @since 1.0.0
     * @return void
    */
    @RabbitListener(queues = "createMain")
    public void msg(String msg){
        //TODO 这里可以做异步的工作
         policyService.insurancePolicy(Long.valueOf(msg));
        System.out.println("消费者消费消息了："+msg);


    }


}
