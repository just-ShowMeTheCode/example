package com.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1310:08
 */
@Slf4j
public class DemoClass {
    public void test(){
        log.info("say hello");
    }

    public static void main(String[] args) {
        DemoClass demoClass = (DemoClass) Enhancer.create(DemoClass.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                log.info("before proxy");
                Object obj = methodProxy.invokeSuper(o, objects);
                log.info("after proxy");
                return obj;
            }
        });

        demoClass.test();
    }
}
