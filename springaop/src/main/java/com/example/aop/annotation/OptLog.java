package com.example.aop.annotation;


import cn.hutool.core.util.StrUtil;
import com.example.aop.enums.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fumj
 * @projectName springboot-aop
 * @description: TODO
 * @date 2020/2/1213:36
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptLog {
    /**
     * 概要内容展示的列名
     */
    String summaryColumnName() ;

    /**
     * 数据库列名，忽略大小写
     *
     * @return
     */
    String columnName() ;

    /**
     * 列名对应的参数名字，
     * 1.如果该值为空，则默认与columnName相同
     * 2.如果不为空，则从参数中取值时使用该参数值
     * @return
     */
    String columnParamterName() default "";

    /**
     * 数据库表名
     * @return
     */
    String tableName() ;

    /**
     * 批量删除是字符串连接符
     * example: 按照id删除时，格式为1,2,3
     * @return
     */
    String strSeparator() default StrUtil.COMMA;

    // 操作类型
    OperationType operationType() ;

}
