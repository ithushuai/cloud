package com.hushuai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * created by it_hushuai
 * 2020/4/9 22:27
 */

/**
 * 不推荐使用@EnableEurekaClient,@EnableEurekaClient只能针对eureka注册中心使用，而@EnableDiscoveryClient可以适配
 * springcloud整合的多种注册中心，zookeeper等也可以使用
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
