package com.example.aop.controller;


import com.example.aop.model.OptLogOriginal;
import com.example.aop.service.OptLogOriginalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/aop/optLogOriginal")
public class OptLogOriginalController {

    private OptLogOriginalService targetService;

    @Autowired
    public OptLogOriginalController(OptLogOriginalService targetService) {
        this.targetService = targetService;
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
        return ResponseEntity.ok(null);
    }


    /**
     * 添加数据
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addItem(@RequestBody OptLogOriginal OptLogOriginal) {
        return ResponseEntity.ok(null);
    }


    /**
     * 更新数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateItem(@RequestBody OptLogOriginal OptLogOriginal) {
        return ResponseEntity.ok(null);
    }


    /**
     * 删除数据
     */
    @RequestMapping("/del")
    @ResponseBody
    public ResponseEntity deleteItems(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(null);
    }
}

