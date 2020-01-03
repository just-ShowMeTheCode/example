package com.example.redis;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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
public class RedisHashTest {

    private static final String COMPOMENT_KEY = "COMPOMENT_KEY";
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test1(){
        Map<String,Object[]> dataMap = new HashMap<>();
        Object[] dataArr = new Object[3];
        for (int i = 0; i < 3; i++) {
            dataArr[i] = i;
        }
        dataMap.put("dataKey",dataArr);
        redisTemplate.opsForHash().put(COMPOMENT_KEY,"hashtest",dataMap);

        Map<String,Object[]> redisDataMap = redisTemplate.opsForHash().entries(COMPOMENT_KEY);
        log.info("=============");
    }


    @Test
    public void test3(){
        Person person = new Person("fumj",20);
        Person person2 = new Person("fumj2",30);
        person2.setName(null);
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(person2);
        redisTemplate.opsForHash().put(COMPOMENT_KEY,"listtest",people);

        List<Person> peopleList = (List<Person>) redisTemplate.opsForHash().get(COMPOMENT_KEY,"listtest");
        log.info("============={}",new Gson().toJson(peopleList));
    }

    @Test
    public void test2(){
        Person person = new Person("fumj",20);
        Person person2 = new Person("fumj2",30);
        person2.setName(null);
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(person2);
        redisTemplate.opsForValue().set("1",people);

        List<Person> list = (List<Person>)redisTemplate.opsForValue().get("1");
        log.info("============={}", new Gson().toJson(list));
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

