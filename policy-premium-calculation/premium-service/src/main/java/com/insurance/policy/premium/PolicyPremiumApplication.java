package com.insurance.policy.premium;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.insurance") //开启feign功能，指定扫描包
public class PolicyPremiumApplication {
    public static void main(String[] args) {
        SpringApplication.run(PolicyPremiumApplication.class, args);
    }
}
