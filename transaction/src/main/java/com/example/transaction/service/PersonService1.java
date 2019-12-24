package com.example.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.transaction.model.Person;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fumj
 * @since 2019-12-17
 */
public interface PersonService1 extends IService<Person> {

    /**
     *
     */
    void transactionRequiredRequired();

    /**
     *
     */
    void transactionRequiredRequiredException();

    /**
     * 异常捕获无效，事务回滚
     */
    void transactionRequiredRequiredExceptionCatch();
    /**
     */
    void transactionRequiredRequiredNew();

    /**
     */
    void transactionRequiredNested();

    /**
     * 异常捕获生效，无需事务回滚
     */
    void transactionRequiredNestedExceptionCatch();

    /**
     * 清空表数据
     */
    void truncateTable();

}
