package com.cloudemo.part.controller;

import com.cloudemo.common.bean.Person;
import com.cloudemo.part.client.DataSourceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> queryApiData(@RequestBody Person person){
        return dataSourceClient.queryApiData(person.getName());
    }

}
