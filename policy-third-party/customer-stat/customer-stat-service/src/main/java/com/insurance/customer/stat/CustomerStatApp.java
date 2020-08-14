package com.insurance.customer.stat;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
public class CustomerStatApp {
    public static void main(String[] args) {
        SpringApplication.run(CustomerStatApp.class,args);
    }
}
