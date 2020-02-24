package com.example.aop.controller;


import com.example.aop.model.OptLogSummary;
import com.example.aop.service.OptLogSummaryService;
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
@RequestMapping("/aop/optLogSummary")
public class OptLogSummaryController {

    private OptLogSummaryService targetService;

    @Autowired
    public OptLogSummaryController(OptLogSummaryService targetService) {
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
    public ResponseEntity addItem(@RequestBody OptLogSummary OptLogSummary) {
        return ResponseEntity.ok(null);
    }


    /**
     * 更新数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateItem(@RequestBody OptLogSummary OptLogSummary) {
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

