package com.demo.httppool.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/5 000511:34
 */
@RestController
@RequestMapping("/test")
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/poolTest")
    public Integer startTest() throws InterruptedException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("first","jinjian");
        jsonObject.put("second","aaaaaaa");

        long start = System.currentTimeMillis();
        //{1} indicates the first placeholder, or name, but this is another getForEntity overload method
        //TestResponseEntity is a custom dto
        ResponseEntity<Integer> entity = restTemplate.getForEntity("http://172.16.16.22:8080/test/randomNumber",
                Integer.class, 123);
        Thread.sleep(RandomUtil.randomInt(10,100));
        long end = System.currentTimeMillis();
        long cost = end - start;
//        System.out.println("Time consuming:"+cost);
        Integer body = entity.getBody();
//        System.out.println("Response body:"+ body);
        return body;
    }


    @GetMapping("/randomNumber")
    public Integer randomNumber() {

        return RandomUtil.randomInt(10,100);

    }
}
