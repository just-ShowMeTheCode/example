package com.rabbit.demo.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/5/2715:16
 */
@Getter
@RequiredArgsConstructor
public enum ExchangeType {
    FANOUT("fanout"),

    DIRECT("direct"),

    TOPIC("topic"),

    DEFAULT(""),

    ;

    private final String type;
}
