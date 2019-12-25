package thread.chapter1;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2515:20
 */
@Slf4j
public class Chapter1 {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Calculator(i)).start();
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        log.info("main thread end {}", StrUtil.repeat("=",24));

    }
}

@Slf4j
class Calculator implements Runnable{
    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= number; i++) {
            log.info("Thread {} \t {} * {} = {}",Thread.currentThread().getId(),number,i,number * i);
        }
        try {
            TimeUnit.SECONDS.sleep(24);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
