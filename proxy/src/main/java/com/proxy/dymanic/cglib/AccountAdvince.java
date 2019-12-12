package com.proxy.dymanic.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1117:48
 */
@Slf4j
public class AccountAdvince implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        return methodProxy.invokeSuper(o,objects);
    }

    public  void before(){
      log.info("enter proxy class");
    }
}
