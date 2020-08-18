package com.woniu.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author ZWL
 * @create 2020/8/17
 * @since 1.0.0
 */
@SpringCloudApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class FileUploadApp {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadApp.class, args);
    }

}