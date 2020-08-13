package com.insurance.policy.premium;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableFeignClients(basePackages = "com.insurance.policy.premium.feign") //开启feign功能，指定扫描包
public class PolicyPremiumApplication {
    public static void main(String[] args) {
        SpringApplication.run(PolicyPremiumApplication.class, args);
    }
}
