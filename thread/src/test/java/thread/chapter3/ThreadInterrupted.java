package thread.chapter3;

import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author fumj
 * @projectName example
 * @description: 启动线程，5s 后中断线程
 * @date 2019/12/2610:05
 */
@Slf4j
public class ThreadInterrupted {
    public static void main(String[] args) {
        Thread thread = new Thread(new PrimeGenerator());
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }

}

@Slf4j
class PrimeGenerator implements Runnable{
    @Override
    public void run() {
        int number = 2;
        while (true){
            if (NumberUtil.isPrimes(number)){
                log.info("prime number {}",number);
            }
            if(isInterrupted()){
                log.info("Thread {} is interrupted",Thread.currentThread().getName());
                return;
            }
            number++;
        }
    }

    private boolean isInterrupted() {
        return Thread.currentThread().isInterrupted();
    }
}