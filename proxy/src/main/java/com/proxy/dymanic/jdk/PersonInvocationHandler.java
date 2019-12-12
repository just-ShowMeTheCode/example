package com.proxy.dymanic.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1115:08
 */
@Slf4j
public class PersonInvocationHandler<T> implements InvocationHandler {
    private Object target;

    public PersonInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MonitorUtil.start();
        log.info("man say invoked at : " + System.currentTimeMillis());
        method.invoke(target, args);
        MonitorUtil.finish();
        return null;
    }
}


