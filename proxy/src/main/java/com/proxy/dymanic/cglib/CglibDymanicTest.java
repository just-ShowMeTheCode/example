package com.proxy.dymanic.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1117:52
 */
public class CglibDymanicTest {
    public static void main(String[] args) {
        AccountService target = new AccountService();
        AccountService accountService = (AccountService) Enhancer.create(target.getClass(), new AccountAdvince());
        accountService.transfer();

//        //创建目标对象
//        AccountService target = new AccountService();
//        //
//        //创建代理对象
//        AccountService proxy = (AccountService) Enhancer.create(target.getClass(),
//                new AccountAdvince());
//        proxy.transfer();

    }
}
