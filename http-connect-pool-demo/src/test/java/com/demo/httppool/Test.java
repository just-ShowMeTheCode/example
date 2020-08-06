package com.demo.httppool;

import cn.hutool.http.HttpUtil;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/5 000516:22
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpUtil.get("http://localhost:8080/test/poolTest");

                }
            }).start();
        }

        Thread.sleep(2000);
    }

}
