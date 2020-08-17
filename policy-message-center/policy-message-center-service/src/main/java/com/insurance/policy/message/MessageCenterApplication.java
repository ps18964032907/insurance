package com.insurance.policy.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @Author sjh
 * @creat 2020/8/13 14:12
 */
@SpringCloudApplication
//@EnableCustomConfig
//@EnableRyFeignClients
//@EnableCustomSwagger2
@EnableFeignClients(basePackages = "com.insurance.policy.message")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MessageCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageCenterApplication.class,args);
    }
}
