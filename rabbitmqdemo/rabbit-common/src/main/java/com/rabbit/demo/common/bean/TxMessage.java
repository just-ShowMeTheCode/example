package com.rabbit.demo.common.bean;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/5/2715:28
 */
public interface TxMessage {

    String businessModule();

    String businessKey();

    String content();
}
