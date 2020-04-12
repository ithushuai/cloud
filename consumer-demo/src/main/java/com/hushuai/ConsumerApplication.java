package com.hushuai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * created by it_hushuai
 * 2020/4/11 13:01
 */
//服务熔断
//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
@SpringCloudApplication //这个注解可以替代上面三个注解
@EnableFeignClients //允许feign调用
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    //Ribbon负载均衡
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
