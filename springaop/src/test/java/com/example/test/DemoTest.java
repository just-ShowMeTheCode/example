package com.example.test;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/2/179:55
 */
public class DemoTest {

    @Test
    public void test1(){
        JSONObject obj = new JSONObject();
        obj.put("success_id",12);
        System.out.println(new Gson().toJson( ResponseEntity.ok(obj)));
    }
}
