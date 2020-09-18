package com.demo.httppool;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/5 000516:22
 */
public class Test {

    public static void main(String[] args) throws Exception {
        testExecutes();
        Thread.sleep(2000);
    }

    public static void testExecutes() throws Exception{
        ExecutorService service = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> taskList = new ArrayList<>();
        taskList.add(new ThreadTask());
        taskList.add(new ThreadTask());
        taskList.add(new ThreadTask());
        taskList.add(new ThreadTask());
        Integer integer = service.invokeAny(taskList);
        System.out.println(integer);

    }


    public void testHttpPool(){
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpUtil.get("http://localhost:8080/test/poolTest");

                }
            }).start();
        }
    }

}

class ThreadTask implements Callable {
    @Override
    public Integer call() {
        int sleepTime = RandomUtil.randomInt(10000,30000);
        System.out.println("start sleep " + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish sleep time ");
        return sleepTime;
    }
}
