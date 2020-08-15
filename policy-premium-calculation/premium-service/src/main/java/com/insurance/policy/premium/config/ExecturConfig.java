package com.insurance.policy.premium.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author mhd
 * @className ExecturConfig
 * @description TODO
 * @create 2020/8/14
 * @since 1.0.0
 */

@Configuration
@EnableAsync
public class ExecturConfig {
    @Bean("taskExector")
    public Executor taskExector() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int i = Runtime.getRuntime().availableProcessors();//获取到服务器的cpu内核
        System.out.println("获取到服务器的cpu内核:"+i);
        executor.setCorePoolSize(i*2);//核心池大小
        executor.setMaxPoolSize(i*2);//最大线程数
        executor.setQueueCapacity(500);//队列程度
        executor.setKeepAliveSeconds(1000);//线程空闲时间
        executor.setThreadNamePrefix("tsak-asyn");//线程前缀名称
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//配置拒绝策略
        return executor;
    }
}
