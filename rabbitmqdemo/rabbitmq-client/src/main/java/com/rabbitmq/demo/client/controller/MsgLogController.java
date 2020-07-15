package com.rabbitmq.demo.client.controller;


import com.rabbitmq.demo.client.model.MsgLog;
import com.rabbitmq.demo.client.service.MsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 消息投递日志 前端控制器
 * </p>
 *
 * @author fumj
 * @since 2020-07-09
 */
@RestController
@RequestMapping("/client/msgLog")
public class MsgLogController {

    private MsgLogService targetService;

    @Autowired
    public MsgLogController(MsgLogService targetService) {
        this.targetService = targetService;
    }


    /**
     * 获取数据列表
     */
    @PostMapping("/page")
    @ResponseBody
    public ResponseEntity page() {
        return ResponseEntity.ok(null);
    }


    /**
     * 获取全部数据
     */
    @PostMapping("/all")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok(null);
    }


    /**
     * 根据ID查找数据
     */
    @PostMapping("/find")
    @ResponseBody
    public ResponseEntity find(@RequestParam("id") Long id) {
        return ResponseEntity.ok(null);
    }


    /**
     * 添加数据
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity addItem(@RequestBody MsgLog MsgLog) {
        return ResponseEntity.ok(null);
    }


    /**
     * 更新数据
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity updateItem(@RequestBody MsgLog MsgLog) {
        return ResponseEntity.ok(null);
    }


    /**
     * 删除数据
     */
    @DeleteMapping("/del")
    @ResponseBody
    public ResponseEntity deleteItems(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(null);
    }
}

