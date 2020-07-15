package com.rabbitmq.demo.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/4/1614:27
 */
@SpringBootApplication
@MapperScan(basePackages = "com.rabbitmq.demo.client.dao")
public class RabbitMqClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqClientApplication.class,args);
    }
}
