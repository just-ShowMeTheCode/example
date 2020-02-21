package com.example.aop.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/2/2111:53
 */
@Data
public class PersonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long personId;

    private String name;

    private Integer age;

    private String email;

    private String address;

}
