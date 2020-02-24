package com.example.aop.service.impl;


import com.example.aop.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author fumj
 * @projectName springboot-aop
 * @description: TODO
 * @date 2020/2/1215:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String findUserNameByTel(String tel) {
        return "zhangsan";
    }
}
