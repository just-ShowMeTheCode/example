package com.springboot.demo.model;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/7/3015:56
 */
public class CglibProxy implements MethodInterceptor {
//    @Override
//    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        Object invoke = methodProxy.invoke(o, objects);
//        return invoke;
//    }
//
//    public Object getProxy(Class clazz){
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(clazz);
//        enhancer.setCallback(this);
//        enhancer.setUseCache(false);
//        return enhancer.create();
//    }


    public Object getProxy(Class clazz) {
        Enhancer enhancer = new Enhancer();
        // 设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        enhancer.setUseCache(false);
        // 通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    // 实现MethodInterceptor接口方法
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("前置代理");
        // 通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("后置代理");
        return result;
    }

}
