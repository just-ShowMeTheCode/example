package com.cloudemo.data;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/314:19
 */
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@SpringCloudApplication
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class,args);
    }
}
