package com.example.aop.enums;

/**
 * @author fumj
 * @projectName springboot-aop
 * @description: TODO
 * @date 2020/2/1213:40
 */
public enum OperationUnit {
    UNKNOWN("unknown"),
    USER("user"),
    EMPLOYEE("employee"),
    REDIS("redis");

    String value;

    public String getValue() {
        return value;
    }

    public OperationUnit setValue(String value) {
        this.value = value;
        return this;
    }

    OperationUnit(String value) {
        this.value = value;
    }
}
