package com.thread.demo1;

import java.util.concurrent.BlockingQueue;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author fumj
 * @version 1.0
 * @date 2022/4/27 14:55
 */
public class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums(){
        if(Math.random() > 0.9){
            return false;
        }

        return true;
    }
}
