package com.proxy.staticproxy;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1110:59
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Lion lion = new Lion("隔壁老王",30);
        Cat cat = new FeederProxy(lion);
        cat.eatFood();
        cat.running();
    }
}
