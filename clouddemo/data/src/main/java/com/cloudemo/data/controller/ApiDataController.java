package com.cloudemo.data.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/816:34
 */
@RestController
@RequestMapping(value = "/api")
public class ApiDataController {

    @PostMapping(value = "/queryApiData")
    public ResponseEntity<String> queryApiData(String name){
        return ResponseEntity.ok(name + " query data success");
    }
}
