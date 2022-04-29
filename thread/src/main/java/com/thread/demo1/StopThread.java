package com.thread.demo1;

import java.util.concurrent.BlockingQueue;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author fumj
 * @version 1.0
 * @date 2022/4/27 14:48
 */
public class StopThread implements Runnable{

    public volatile boolean canceled = false;

    BlockingQueue storage;

    public StopThread(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {

            while (!canceled && num < 1000){
            if(num % 10 == 0){
                storage.put(num);
                System.out.println(num + "是10的倍数");
            }
            num++;
                Thread.sleep(1);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者结束");
        }
    }
}
