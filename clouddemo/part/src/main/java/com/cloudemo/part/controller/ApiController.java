package com.cloudemo.part.controller;

import com.cloudemo.common.bean.Person;
import com.cloudemo.part.client.DataSourceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private DataSourceClient dataSourceClient;

    @PostMapping(value = "/queryApiData")
    public ResponseEntity<String> queryApiData(@RequestBody Person person){
        return dataSourceClient.queryApiData(person.getName());
    }

}
