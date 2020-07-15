package com.rabbit.demo.common.bean;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/7/815:34
 */
@Data
public class Person {

    private String name;
    private Integer age;
    private LocalDate birthday;

}
