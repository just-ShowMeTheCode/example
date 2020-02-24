package com.example.aop.controller;


import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.aop.annotation.OptLog;
import com.example.aop.dao.PersonMapper;
import com.example.aop.enums.OperationType;
import com.example.aop.model.Person;
import com.example.aop.model.vo.PersonVo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fumj
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/aop/person")
@Slf4j
public class PersonController {

    private final PersonMapper personMapper;
    private static final String SUCCESS_ID = "success_id";

    @Autowired
    public PersonController(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }


    /**
     * 获取数据列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseEntity findListByPage() {
        return ResponseEntity.ok(null);
    }


    /**
     * 获取全部数据
     */
    @RequestMapping("/all")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok(null);
    }


    /**
     * 根据ID查找数据
     */
    @RequestMapping("/find")
    @ResponseBody
    public ResponseEntity find(@RequestParam("id") Long id) {

        return ResponseEntity.ok(personMapper.selectById(id));
    }


    /**
     * 添加数据
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @OptLog(summaryColumnName = "name",columnName = "id",tableName = "t_person",
            operationType = OperationType.INSERT)
    public ResponseEntity addItem(@RequestBody Person Person) {
        Person person = new Person();
        person.setName(RandomUtil.randomString("abcdefgh",4));
        person.setAge(34);
        person.setEmail(RandomUtil.randomString("12345",4) + "@126.com");
        person.setAddress(RandomUtil.randomString("ABCDEF",4));
        personMapper.insert(person);
        JSONObject obj = new JSONObject();
        obj.put(SUCCESS_ID,person.getId());
        return ResponseEntity.ok(obj);
    }


    /**
     * 更新数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @OptLog(summaryColumnName = "name",columnName = "id",columnParamterName = "personId",
            tableName = "t_person",
            operationType = OperationType.UPDATE)
    public ResponseEntity updateItem(@RequestBody PersonVo personVo) {
        Person person = personMapper.selectById(personVo.getPersonId());
        BeanUtils.copyProperties(personVo,person);
        log.info("========================");
        log.info(new Gson().toJson(person));
        personMapper.updateById(person);
        return ResponseEntity.ok("success");
    }



    /**
     * 删除数据
     */
    @DeleteMapping("/delStr")
    @OptLog(summaryColumnName = "name",columnName = "id",columnParamterName = "ids",tableName = "t_person",
            operationType = OperationType.DELETE)
    public ResponseEntity deleteItems(@RequestParam("ids") String ids) {
        String[] arr = ids.split(",");
        List<Long> idList = new ArrayList<>(arr.length);
        for(String id : arr){
            idList.add(Long.valueOf(id));
        }
        personMapper.deleteBatchIds(idList);
        return ResponseEntity.ok("success");
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delArr")
    @ResponseBody
    @OptLog(summaryColumnName = "name",columnName = "id",columnParamterName = "ids",tableName = "t_person",
            operationType = OperationType.DELETE)
    public ResponseEntity deleteArrItems(@RequestParam("ids") Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        personMapper.deleteBatchIds(idList);
        return ResponseEntity.ok("success");
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delList")
    @ResponseBody
    @OptLog(summaryColumnName = "name",columnName = "id",columnParamterName = "ids",tableName = "t_person",
            operationType = OperationType.DELETE)
    public ResponseEntity deleteListItems(@RequestParam("ids") List<Long> ids) {
        personMapper.deleteBatchIds(ids);
        return ResponseEntity.ok("success");
    }
}

