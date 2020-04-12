package com.hushuai.client;

import com.hushuai.pojo.User;
import org.springframework.stereotype.Component;

/**
 * UserFeignClient的实现类，用于编写熔断回调方法，feign客户端中如果调用远程服务出现熔断，则会回调这里的相同方法
 * created by it_hushuai
 * 2020/4/12 21:50
 */
@Component
public class UserFeignClientHystrix implements UserFeignClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setUsername("服务繁忙！");
        return user;
    }
}
