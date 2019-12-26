package thread.chapter2;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author fumj
 * @projectName example
 * @description: 打印现在状态变化
 * @date 2019/12/2522:29
 */
@Slf4j
public class Chapter2 {
    public static void main(String[] args) {
        int number = 20;
        Thread[] threads = new Thread[number];
        Thread.State[] states = new Thread.State[number];
        for (int i = 0; i < number; i++) {
            threads[i] = new Thread(new Calulator(number));
            states[i] = threads[i].getState();
        }
        for (int i = 0; i < number; i++) {
            threads[i].start();
        }
        boolean finish = false;
        while (!finish){
            for (int i = 0; i < number; i++) {
                if(!threads[i].getState().equals(states[i])){
                    log.info("thread {} state changed. old state {},new state {}",threads[i].getId(),
                            states[i],threads[i].getState());
                    states[i] = threads[i].getState();
                }
            }
            finish = true;
            for (int i = 0; i < number; i++) {
                finish = finish && Thread.State.TERMINATED.equals(threads[i].getState());
            }

        }

        log.info("main thread end {}", StrUtil.repeat('=',24));
    }
}
@Slf4j
class Calulator implements Runnable{
    private int number;

    public Calulator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= number; i++) {
//            log.info("Thread {} {} * {} = {}",Thread.currentThread().getName(),i,number,i * number);
            try {
                TimeUnit.SECONDS.sleep(RandomUtil.randomInt(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
