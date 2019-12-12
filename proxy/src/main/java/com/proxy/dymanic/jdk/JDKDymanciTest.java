package com.proxy.dymanic.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1115:39
 */
@Slf4j
public class JDKDymanciTest {
    public static void main(String[] args) {
        log.info("first proxy method");
        Person person = new SoftwareEngineer();
        InvocationHandler invocationHandler = new PersonInvocationHandler<>(person);
        Person proxyPerson = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(),new Class[]{Person.class},
                invocationHandler);
        proxyPerson.say();
    }
}
