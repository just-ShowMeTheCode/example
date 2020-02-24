package com.springboot.demo.controller;


import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.aop.annotation.OptLog;
import com.example.aop.enums.OperationType;
import com.google.gson.Gson;
import com.springboot.demo.dao.CompanyMapper;
import com.springboot.demo.model.Company;
import com.springboot.demo.model.vo.CompanyVo;
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
 * @since 2020-02-21
 */
@RestController
@RequestMapping("/demo/company")
@Slf4j
public class CompanyController {

    private static final String SUCCESS_ID = "success_id";
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyController(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
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

        return ResponseEntity.ok(companyMapper.selectById(id));
    }


    /**
     * 添加数据
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @OptLog(summaryColumnName = "name",columnName = "company_id",tableName = "t_company",
            operationType = OperationType.INSERT)
    public ResponseEntity addItem(@RequestBody Company Company) {
        Company company = new Company();
        company.setName(RandomUtil.randomString("abcdefgh",4));
        company.setTele(RandomUtil.randomString("1234567890",8));
        company.setAddress(RandomUtil.randomString("ABCDEF",4));
        companyMapper.insert(company);
        JSONObject obj = new JSONObject();
        obj.put(SUCCESS_ID,company.getCompanyId());
        return ResponseEntity.ok(obj);


    }


    /**
     * 更新数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @OptLog(summaryColumnName = "name",columnName = "company_id",columnParamterName = "id",
            tableName = "t_company",
            operationType = OperationType.UPDATE)
    public ResponseEntity updateItem(@RequestBody CompanyVo companyVo) {
        Company company = companyMapper.selectById(companyVo.getId());
        BeanUtils.copyProperties(companyVo,company);
        log.info("========================");
        log.info(new Gson().toJson(company));
        companyMapper.updateById(company);
        return ResponseEntity.ok("success");
    }


    /**
     * 删除数据
     */
    @RequestMapping("/delStr")
    @ResponseBody
    @OptLog(summaryColumnName = "name",columnName = "company_id",
            columnParamterName = "ids",tableName = "t_company",
            operationType = OperationType.DELETE)
    public ResponseEntity deleteItems(@RequestParam("ids") String ids) {
        String[] arr = ids.split(",");
        List<Long> idList = new ArrayList<>(arr.length);
        for(String id : arr){
            idList.add(Long.valueOf(id));
        }
        companyMapper.deleteBatchIds(idList);
        return ResponseEntity.ok("success");

    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delArr")
    @ResponseBody
    @OptLog(summaryColumnName = "name",columnName = "company_id",
            columnParamterName = "ids",tableName = "t_company",
            operationType = OperationType.DELETE)
    public ResponseEntity deleteArrItems(@RequestParam("ids") Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        companyMapper.deleteBatchIds(idList);
        return ResponseEntity.ok("success");
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delList")
    @ResponseBody
    @OptLog(summaryColumnName = "name",columnName = "company_id",
            columnParamterName = "ids",tableName = "t_company",
            operationType = OperationType.DELETE)
    public ResponseEntity deleteListItems(@RequestParam("ids") List<Long> ids) {
        companyMapper.deleteBatchIds(ids);
        return ResponseEntity.ok("success");
    }

}

