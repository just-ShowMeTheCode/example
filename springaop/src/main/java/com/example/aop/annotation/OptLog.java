package com.example.aop.annotation;



import com.example.aop.enums.OperationType;
import com.example.aop.enums.OperationUnit;

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
    // 方法描述，可以使用占位符{{}}
    String detail() default "";
    // 日志登等级，1-9
    int level() default 0;
    // 操作类型
    OperationType operationType() default OperationType.UNKNOWN;
    // 被操作的对象
    OperationUnit operationUnit() default OperationUnit.UNKNOWN;
}
