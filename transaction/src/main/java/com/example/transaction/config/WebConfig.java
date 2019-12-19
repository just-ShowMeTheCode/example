package com.example.transaction.config;

import com.example.transaction.interceptor.InterceptorOfInitTablePerson;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1814:26
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    InterceptorOfInitTablePerson interceptorOfInitTablePerson;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorOfInitTablePerson);
    }
}
