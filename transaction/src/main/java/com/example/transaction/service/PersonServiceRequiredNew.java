package com.example.transaction.service;

import com.example.transaction.model.Person;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2310:16
 */
public interface PersonServiceRequiredNew {
    /**
     * 无事务控制
     * add1 创建新事务，success
     * add2 创建新事务，success
     */
    void noTransactionRequiredNewRequiredNew();
    /**
     * 无事务控制
     * add1 创建新事务，success
     * add2 创建新事务，fail，rollback
     * add3 未执行
     */
    void noTransactionRequiredNewRequiredNewException();
    /**
     * 新建事务控制
     * add1 创建新事务，success
     * add2 创建新事务，success
     */
    void transactionRequiredNewRequiredNew();
    /**
     * 新建事务控制
     * add1 创建新事务，success
     * add2 创建新事务，fail,rollback
     * add3 未执行
     */
    void transactionRequiredNewRequiredNewException();

    /**
     * 新建事务控制
     * add1 创建新事务，success
     * add2 创建新事务，success
     */
    void transactionRequiredNewRequiredNewException2();

    /**
     * 清空表数据
     */
    void truncateTable();
}
