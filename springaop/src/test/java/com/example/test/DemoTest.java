package com.example.test;

import org.junit.Test;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/2/179:55
 */
public class DemoTest {

    @Test
    public void test1(){
        Object[] arr = new String[3];
        System.out.println(arr.getClass().isArray());
    }
}
