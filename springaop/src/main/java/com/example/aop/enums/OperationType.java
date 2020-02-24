package com.example.aop.enums;

/**
 * @author fumj
 * @projectName springboot-aop
 * @description: TODO
 * @date 2020/2/1213:37
 */
public enum OperationType {
    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private String value;

    public String getValue() {
        return value;
    }

    public OperationType setValue(String value) {
        this.value = value;
        return this;
    }

    OperationType(String value) {
        this.value = value;
    }
}
