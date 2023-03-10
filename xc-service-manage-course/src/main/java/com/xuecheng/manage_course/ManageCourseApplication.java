package com.xuecheng.manage_course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author User
 * @date 2019/11/23
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan("com.xuecheng.framework.domain.course")
@ComponentScan(basePackages = {"com.xuecheng.api"})
@ComponentScan(basePackages = {"com.xuecheng.manage_course"})
@ComponentScan(basePackages = {"com.xuecheng.framework"})
public class ManageCourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCourseApplication.class, args);
    }
}
