package com.hushuai.auth.controller;

import com.hushuai.auth.config.JwtProperties;
import com.hushuai.auth.service.AuthService;
import com.hushuai.auth.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by it_hushuai
 * 2020/8/10 22:47
 */
@RestController
@RequestMapping("auth")
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private JwtProperties prop;

    @Autowired
    private AuthService authService;

    /**
     * 登录授权功能
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<Void> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletResponse response,
            HttpServletRequest request) {
        String token = authService.login(username, password);
        // httpOnly：true，防止通过js获取和修改
        CookieUtils.setCookie(request, response, prop.getCookieName(), token, -1, true);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
