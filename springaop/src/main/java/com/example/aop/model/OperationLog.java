package com.example.aop.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fumj
 * @projectName springboot-aop
 * @description: TODO
 * @date 2020/2/1213:48
 */
@Data
@ToString
public class OperationLog {



    private String method;

    private Date createTime;
    /**
     * 列名
     */
    private String columnName;

    private String tableName;

    private Object columnValue;

    /**
     * 数据库列名对应的参数名
     */
    private String columnParamterName;


    /**
     * 参数名和参数值
     */
    private Map<String,Object> requestParamsAndValue;

    /**
     * 保存方法执行前数据库中存在的数据
     */
    private List<Map<String,Object>> beforeOperatorDatabaseRecordList = new ArrayList<>();

    /**
     * 保存方法执行后数据库中存在的数据
     */
    private List<Map<String,Object>> afterOperatorDatabaseRecordList = new ArrayList<>();

    private String userId = "1";

    private String userName = "fumj";

    private String ip;

    private String operationType;

    private Long runTime;

    private String returnValue;



    /**
     * 详情展示的字段名
     */
    private String summaryColumnName;

    /**
     * 详情展示的字段名值
     */

    private Object summaryColumnValue;


    private String differenceFieldValue;



}
