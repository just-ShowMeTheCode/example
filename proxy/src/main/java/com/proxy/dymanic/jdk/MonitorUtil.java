package com.proxy.dymanic.jdk;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1115:14
 */
@Slf4j
public class MonitorUtil {
    private static ThreadLocal<Long> t = new ThreadLocal<>();

    public static void start(){
        t.set(System.nanoTime());
    }

    public static void finish(){
      log.info("cost time {}",System.nanoTime() - t.get());
    }
}
