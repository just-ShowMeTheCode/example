package com.clouddemo.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/314:15
 */
@SpringCloudApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApplicatioin {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplicatioin.class,args);
    }
}
