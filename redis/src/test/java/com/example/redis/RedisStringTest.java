package com.example.redis;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/3110:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@Slf4j
public class RedisStringTest {

    private static final String COMPOMENT_KEY = "COMPOMENT_KEY";
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1(){
        Map<String,Object[]> dataMap = new HashMap<>();
        Object[] dataArr = new Object[3];
        for (int i = 0; i < 3; i++) {
            dataArr[i] = i;
        }
        dataMap.put("dataKey",dataArr);
        stringRedisTemplate.opsForValue().set("stringtest",new Gson().toJson(dataMap));

        Map<String,Object[]> redisDataMap = (Map<String,Object[]>)new Gson().fromJson(stringRedisTemplate.opsForValue().get("stringtest"),
                Map.class);
        log.info("============={}",new Gson().toJson(redisDataMap));
    }




    class Person{
        private String name;
        private int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Person setName(String name) {
            this.name = name;
            return this;
        }

        public int getAge() {
            return age;
        }

        public Person setAge(int age) {
            this.age = age;
            return this;
        }
    }
}

