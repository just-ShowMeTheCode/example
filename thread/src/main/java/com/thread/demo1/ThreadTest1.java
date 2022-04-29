package com.thread.demo1;

import com.sun.deploy.uitoolkit.impl.awt.AWTDragHelper;
import oracle.jvm.hotspot.jfr.Producer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author fumj
 * @version 1.0
 * @date 2022/4/27 14:57
 */
public class ThreadTest1 {
    public static void main(String[] args) throws Exception{
        ArrayBlockingQueue storage = new ArrayBlockingQueue(8);
        StopThread producer = new StopThread(storage);
        Consumer consumer = new Consumer(storage);

        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(500);

        while(consumer.needMoreNums()){
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }

        System.out.println("消费者不需要更多数据了。");
        producer.canceled = true;

        System.out.println(producer.canceled);
    }
}
