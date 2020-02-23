package com.springboot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/2/2115:01
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.springboot.demo.dao","com.example.aop.dao"})
@ComponentScan(basePackages = {"com.example.aop.aop","com.springboot.demo"})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}
