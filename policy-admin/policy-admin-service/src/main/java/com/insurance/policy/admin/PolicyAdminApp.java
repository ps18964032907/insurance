package com.insurance.policy.admin;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCustomConfig
@EnableRyFeignClients
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.insurance.policy.premium.feign","com.ruoyi","com.insurance.platform.api","com.insurance.policy.pay.feign","com.insurance.policy.message.fegin"}) //开启feign功能，指定扫描包
@MapperScan(basePackages={"com.insurance.policy.admin.mapper"})
public class PolicyAdminApp {
    public static void main(String[] args) {
        SpringApplication.run(PolicyAdminApp.class,args);
    }
}
