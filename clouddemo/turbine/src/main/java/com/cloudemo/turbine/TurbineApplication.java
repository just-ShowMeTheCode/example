package com.cloudemo.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/816:22
 */
@EnableTurbine
@SpringCloudApplication
public class TurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class,args);
    }
}
