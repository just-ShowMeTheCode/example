package com.springboot.demo.model.vo;

import lombok.Data;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/2/2114:49
 */
@Data
public class CompanyVo {

    private Long id;

    private String name;

    private String address;

    private String tele;
}
