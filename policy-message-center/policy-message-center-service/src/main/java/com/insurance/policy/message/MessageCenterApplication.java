package com.insurance.policy.message;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author sjh
 * @creat 2020/8/13 14:12
 */
@SpringCloudApplication
//@EnableFeignClients(basePackages = "com.insurance.policy.message.fegin")
public class MessageCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageCenterApplication.class,args);
    }
}
