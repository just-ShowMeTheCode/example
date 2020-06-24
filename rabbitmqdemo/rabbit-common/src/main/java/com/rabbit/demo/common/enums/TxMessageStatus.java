package com.rabbit.demo.common.enums;

import lombok.RequiredArgsConstructor;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/5/2715:29
 */
@RequiredArgsConstructor
public enum  TxMessageStatus {
    /**
     * 成功
     */
    SUCCESS(1),

    /**
     * 待处理
     */
    PENDING(0),

    /**
     * 处理失败
     */
    FAIL(-1),

    ;

    private final Integer status;
}
