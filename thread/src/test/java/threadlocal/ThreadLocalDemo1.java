package threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fumj
 * @projectName example
 * @description: 不同的线程中调用同一个类对象的get()方法，输出依据线程的不同而不同。
 * @date 2019/12/2510:21
 */
@Slf4j
public class ThreadLocalDemo1 {
    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "default";
        }
    };

    private static ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return 0L;
        }
    };

    public static void main(String[] args) {
        stringThreadLocal.set("main thread");
        longThreadLocal.set(1L);

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("new thread {}:",stringThreadLocal.get());
                log.info("new thread {}:",longThreadLocal.get());
            }
        }).start();

        log.info("main thread {}:",stringThreadLocal.get());
        log.info("main thread {}:",longThreadLocal.get());


    }
}
