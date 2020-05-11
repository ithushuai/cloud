package com.hushuai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by it_hushuai
 * 2020/5/8 20:32
 */
@RestController
@RefreshScope //开启刷新功能,在使用到配置的类上加。不使用bus时，使用当前服务地址，单独访问http://localhost:8085/actuator/refresh，即可实现本服务配置刷新
public class GitController {
    @Value("${user.password}")
    private String password;

    @RequestMapping("/show")
    public String getGitConfig(){
        return password;
    }
}
