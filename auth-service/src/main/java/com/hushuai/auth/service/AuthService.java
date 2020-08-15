package com.hushuai.auth.service;

import com.hushuai.auth.config.JwtProperties;
import com.hushuai.auth.utils.JwtUtils;
import com.hushuai.auth.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by it_hushuai
 * 2020/8/10 22:55
 */
@Service
public class AuthService {
    @Autowired
    private JwtProperties properties;

    public String login(String username, String password) {
        try {
            if("zhangsan".equals(username) && !"123456".equals(password)) {
                throw new RuntimeException("用户名或密码错误");
            }
            username = "zhangsan";
            password = "123456";
            UserInfo userInfo = new UserInfo(1L, username);
            // 生成token
            String token = JwtUtils.generateToken(userInfo, properties.getPrivateKey(), properties.getExpire());
            return token;
        } catch (Exception e) {
            throw new RuntimeException("用户凭证生成失败");
        }
    }
}
