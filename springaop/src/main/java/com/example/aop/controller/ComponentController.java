package com.example.aop.controller;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.aop.annotation.OptLog;
import com.example.aop.dao.ApiLogMapper;
import com.example.aop.dao.ComponentMapper;
import com.example.aop.enums.OperationType;
import com.example.aop.model.Component;
import com.example.aop.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 零件表 前端控制器
 * </p>
 *
 * @author fumj
 * @since 2020-02-13
 */
@RestController
@RequestMapping("component")
public class ComponentController {

    private final ComponentService componentService;

    private final ComponentMapper componentMapper;

    private final ApiLogMapper apiLogMapper;

    @Autowired
    public ComponentController(ComponentService targetService, ComponentMapper componentMapper,
                               ApiLogMapper apiLogMapper) {
        this.componentService = targetService;
        this.componentMapper = componentMapper;
        this.apiLogMapper = apiLogMapper;
    }


    /**
     * 根据ID查找数据
     */
    @RequestMapping("/find")
    @ResponseBody
    public ResponseEntity find(@RequestParam("id") Long id) {
        return ResponseEntity.ok(componentMapper.selectById(id));
    }


    /**
     * 添加数据
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @OptLog(summaryColumnName = "component_name",columnName = "COMPONENT_ID",columnParamterName = "componentId",
            tableName = "t_component",operationType = OperationType.INSERT)
    public ResponseEntity addItem(@RequestBody Component component) {
        component.setComponentNo("component_"+IdWorker.getId());
        component.setOperator(1);
        component.setOriginator(1);
        componentMapper.insert(component);
        return ResponseEntity.ok("success" + component.getComponentId());
    }


    /**
     * 更新数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @OptLog(summaryColumnName = "component_name",columnName = "COMPONENT_ID",columnParamterName = "componentId",
            tableName = "t_component",operationType = OperationType.UPDATE)
    public ResponseEntity updateItem(@RequestBody Component Component) {

        return ResponseEntity.ok("success");
    }


    /**
     * 删除数据
     */
    @DeleteMapping("/delStr")
    @OptLog(summaryColumnName = "component_name",columnName = "COMPONENT_ID",columnParamterName = "ids",
            tableName = "t_component",
            operationType = OperationType.DELETE)
    public ResponseEntity deleteItems(@RequestParam("ids") String ids) {

        return ResponseEntity.ok("success");
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delArr")
    @ResponseBody
    @OptLog(summaryColumnName = "component_name",columnName = "COMPONENT_ID",columnParamterName = "ids",
            tableName = "t_component",operationType = OperationType.DELETE)
    public ResponseEntity deleteArrItems(@RequestParam("ids") Long[] ids) {

        return ResponseEntity.ok("success");
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delList")
    @ResponseBody
    @OptLog(summaryColumnName = "component_name",columnName = "COMPONENT_ID",columnParamterName = "ids",
            tableName = "t_component",operationType = OperationType.DELETE)
    public ResponseEntity deleteListItems(@RequestParam("ids") List<Long> ids) {

        return ResponseEntity.ok("success");
    }
}

