package com.insurance.policy.pay;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import io.swagger.annotations.SwaggerDefinition;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jiangshuai
 * @date 2020/8/13 0013 14:38
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringCloudApplication
@MapperScan(basePackages={"com.insurance.policy.pay.mapper"})
public class PolicyPayApp {

    public static void main(String[] args) {
        SpringApplication.run(PolicyPayApp.class, args);
    }
}
