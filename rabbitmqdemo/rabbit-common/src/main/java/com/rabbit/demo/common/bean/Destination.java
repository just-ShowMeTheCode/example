package com.rabbit.demo.common.bean;

import com.rabbit.demo.common.enums.ExchangeType;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/5/2715:23
 */
public interface Destination {
    ExchangeType exchangeType();

    String queueName();

    String exchangeName();

    String routingKey();
}
