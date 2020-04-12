package com.hushuai.client;

import com.hushuai.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign客户端，模拟调用本地接口
 * created by it_hushuai
 * 2020/4/12 20:35
 */
@FeignClient(value = "user-service", fallback = UserFeignClientHystrix.class) //表明当前接口是一个feign客户端，负责调用其他服务的接口, 参数value表示从注册中心调用哪个微服务，feigin会根据ribbon负载均衡调用服务实例;UserFeignClientHystrix指定熔断回调类
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    User queryById(@PathVariable("id") Long id);
}
