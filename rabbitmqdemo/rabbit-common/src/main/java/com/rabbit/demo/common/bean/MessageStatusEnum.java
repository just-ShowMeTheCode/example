package com.rabbit.demo.common.bean;

import com.rabbit.demo.common.enums.CodeEnum;
import lombok.Getter;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/7/815:39
 */
@Getter
public enum  MessageStatusEnum implements CodeEnum {

    STATE1(1,"已提交"),

    STATE2(2,"已消费"),

    STATE3(3,"提交失败"),




    ;


    private Integer code;

    private String msg;

    MessageStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
