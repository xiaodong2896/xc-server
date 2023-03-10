package com.xuecheng.manage.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-06-23 11:53
 **/
@EnableDiscoveryClient//从Eureka Server获取服务
@SpringBootApplication//扫描所在包及子包的bean，注入到ioc中
@EntityScan("com.xuecheng.framework.domain.media")
@ComponentScan(basePackages = {"com.xuecheng.api"})
@ComponentScan(basePackages = {"com.xuecheng.framework"})//扫描framework中通用类
@ComponentScan(basePackages = {"com.xuecheng.manage.media"})//扫描本项目下的所有类
public class ManageMediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageMediaApplication.class, args);
    }
}
