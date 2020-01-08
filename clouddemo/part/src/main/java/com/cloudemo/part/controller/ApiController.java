package com.cloudemo.part.controller;

import com.cloudemo.part.client.DataSourceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/716:05
 */
@RestController
@RequestMapping(value = "/api")
@Slf4j
public class ApiController {

    @Autowired
    private DataSourceClient dataSourceClient;

    @PostMapping(value = "/queryApiData")
    public ResponseEntity<String> queryApiData(@RequestParam(value = "name")String name){
        return dataSourceClient.queryApiData(name);
    }

}
