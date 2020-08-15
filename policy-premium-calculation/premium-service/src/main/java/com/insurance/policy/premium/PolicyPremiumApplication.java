package com.insurance.policy.premium;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringCloudApplication
@EnableFeignClients(basePackages = "com.insurance") //开启feign功能，指定扫描包
public class PolicyPremiumApplication {
    public static void main(String[] args) {
        SpringApplication.run(PolicyPremiumApplication.class, args);
    }
}
