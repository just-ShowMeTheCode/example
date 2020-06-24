package com.rabbit.demo.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/4/1614:45
 */
@Data
public class OrderBillingDto implements Serializable {

    private LocalDateTime tradeTime;
    private String orderNo;
    private String serveNo;
    private String transactionSerialNumber;
}
