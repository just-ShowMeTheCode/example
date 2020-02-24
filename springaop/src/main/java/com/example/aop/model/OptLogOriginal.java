package com.example.aop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author fumj
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_opt_log_original")
public class OptLogOriginal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "opt_log_original_id", type = IdType.ID_WORKER)
    private Long optLogOriginalId;

    private Long optLogId;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 查询列名
     */
    private String columnName;

    /**
     * 查询表名
     */
    private String tableName;

    /**
     * 查询列值
     */
    private String columnValue;

    /**
     * 查询列对应参数名
     */
    private String columnParamterName;

    /**
     * 请求参数和值
     */
    private String requestParamsAndValue;

    /**
     * 操作前数据库值
     */
    private String beforeOperatorData;

    /**
     * 操作后数据库值
     */
    private String afterOperatorData;

    /**
     * 返回结果
     */
    private String returnValue;

    /**
     * 需要展示的列名
     */
    private String summaryColumnName;

    /**
     * 需要展示列名对应的值
     */
    private String summaryColumnValue;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
