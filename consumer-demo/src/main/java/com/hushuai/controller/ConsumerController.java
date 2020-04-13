package com.hushuai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hushuai.client.UserFeignClient;
import com.hushuai.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * created by it_hushuai
 * 2020/4/11 19:29
 */
@RestController
@RequestMapping("consume")
@DefaultProperties(defaultFallback = "globalRequestError") //指定统一定义熔断回调方法
public class ConsumerController {
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private UserFeignClient userFeignClient; //引入fegin客户端

    /*@RequestMapping("/{id}")
    @HystrixCommand(fallbackMethod = "queryByIdError" //指定熔断后的回调方法，fallbackMethod单独指定针对该方法的回调方法，也可不加，在类上统一指定回调方法
//            commandProperties = {//给熔断功能设置属性
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")}//熔断超时时间
    )
    public String queryById(@PathVariable("id") Long id) throws JsonProcessingException {
        //ribbon负载均衡对restTemplate请求进行拦截，将user-service替换成相应的服务实例
        String url = "http://user-service/user/" + id;
        String user = restTemplate.getForObject(url, String.class);
        return user;
    }*/

    /**
     * 调用feign客户端方法
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id){
        User user = userFeignClient.queryById(id);
        return user; //fegin客户端调用
    }

    /**
     * 服务熔断后的回调方法，如果调用其他微服务出现超时，调用方可熔断，提示服务错误
     * 要求：回调方法与原方法返回值和参数一致!!!
     * @param id
     * @return
     */
    public String queryByIdError(Long id){
        return "不好意思，服务器太拥挤";
    }

    /**
     * 统一回调方法，参数不限制
     * @return
     */
    public String globalRequestError(){
        return "不好意思，服务器太拥挤";
    }
}
