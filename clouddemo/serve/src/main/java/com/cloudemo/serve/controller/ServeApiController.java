package com.cloudemo.serve.controller;

import com.cloudemo.common.bean.Person;
import com.cloudemo.serve.client.PartApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/716:16
 */
@RestController
@RequestMapping(value = "/api")
@Slf4j
public class ServeApiController {

    @Resource
    private PartApiService partApiService;

    @PostMapping(value = "/queryApiData")
    public ResponseEntity queryApiData(@RequestParam(name = "name") String name){
        Person person = new Person(name);
      ResponseEntity entity = partApiService.queryApiData(person);
      return entity;
    }

    @PostMapping(value = "/test")
    public ResponseEntity test(String name){
        return ResponseEntity.ok("test");
    }
}
