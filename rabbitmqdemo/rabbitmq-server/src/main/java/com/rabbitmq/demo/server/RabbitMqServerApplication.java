package com.rabbitmq.demo.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/4/1614:28
 */
@SpringBootApplication
@MapperScan(basePackages = "com.rabbitmq.demo.server.dao")
public class RabbitMqServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqServerApplication.class,args);
    }
}
