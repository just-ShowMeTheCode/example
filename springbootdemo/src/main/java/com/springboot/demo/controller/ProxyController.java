package com.springboot.demo.controller;

/**
 * @author fumj
 * @projectName example
 * @description: 测试matespace空间上升时，触发fullgc问题
 * @date 2020/7/3016:00、
 * https://club.perfma.com/article/1700576
 */

import com.springboot.demo.model.CglibProxy;
import com.springboot.demo.model.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/proxy" )
@RestController
public class ProxyController {

    @GetMapping("/createHelloProxy")
    public void createHelloProxy(){
//        CglibProxy cglibProxy = new CglibProxy();
//        for (int i = 0; i < 2000; i++) {
//            System.out.println(i);
//            Hello hello = (Hello)cglibProxy.getProxy(Hello.class);
//            hello.sayHello();
//        }

        CglibProxy proxy = new CglibProxy();
            for (int i = 0; i < 10000; i++) {
                   //通过生成子类的方式创建代理类
                Hello proxyTmp = (Hello) proxy.getProxy(Hello.class);
                   proxyTmp.sayHello();
               }
    }

}
