package threadlocal;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author fumj
 * @projectName example
 * @description: 在线程类中定义变量，如果不是用threadloal 关键字，所有结束时间都是相同的，
 * 使用ThreadLocal ，可以保持各自时间变量独立，结束时间各不相同
 * @date 2019/12/2510:21
 */
@Slf4j
public class ThreadLocalDemo3 {
    public static void main(String[] args) {
        UnsafeRun unsafeRun = new UnsafeRun();
        for (int i = 0; i < 3; i++) {
            new Thread(unsafeRun).start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{}",StrUtil.repeat('=',24));
        SafeRun safeRun = new SafeRun();
        for (int i = 0; i < 3; i++) {
            new Thread(safeRun).start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

@Slf4j
class UnsafeRun implements Runnable {
    private Date startDate;

    @Override
    public void run() {
        startDate = new Date();
        log.info("Thread Id {} start date {}",Thread.currentThread().getId(),
                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString());
        try {
            TimeUnit.SECONDS.sleep(4 + RandomUtil.randomInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Thread Id {} end date {}",Thread.currentThread().getId(),
                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString());
    }
}

@Slf4j
class SafeRun implements Runnable{
    private ThreadLocal<Date> dateThreadLocal = new ThreadLocal<Date>(){
        @Override
        protected Date initialValue() {
            return new Date();
        }
    };
    @Override
    public void run() {
        log.info("Thread Id {} start date {}",Thread.currentThread().getId(),
                dateThreadLocal.get().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString());
        try {
            TimeUnit.SECONDS.sleep(4 + RandomUtil.randomInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Thread Id {} end date {}",Thread.currentThread().getId(),
                dateThreadLocal.get().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString());
    }
}
