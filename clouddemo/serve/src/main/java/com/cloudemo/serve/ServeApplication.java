package com.cloudemo.serve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/314:19
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableDiscoveryClient
@EnableHystrixDashboard
@SpringBootApplication
@EnableScheduling

public class ServeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServeApplication.class,args);
    }
}
