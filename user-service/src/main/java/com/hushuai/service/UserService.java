package com.hushuai.service;

import com.hushuai.pojo.User;
import com.hushuai.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by it_hushuai
 * 2020/4/9 22:32
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User queryById(Long id){
        try {
            Thread.sleep(2000);//模拟服务超时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userRepository.findById(id).orElse(null);
    }
}
