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
public interface PersonServiceNested extends IService<Person> {
    /**
     * 无事务控制
     * add1 创建新事务，success
     * add2 创建新事务，success
     */
    void noTransactionNestedNested();
    /**
     * 无事务控制
     * add1 创建新事务，success
     * add2 创建新事务，fail，rollback
     * add3 未执行
     */
    void noTransactionNestedNestedException();
    /**
     * 新建事务控制
     * add1 事务嵌套，使用已存在的事务，success
     * add2 使用已存在的事务，success
     */
    void transactionNestedNested();
    /**
     * 新建事务控制
     * add1 使用已存在的事务，success
     * add2 使用已存在的事务，fail,rollback
     * add3 未执行
     */
    void transactionNestedNestedException();

    /**
     * 新建事务控制
     * add1 使用已存在的事务，rollback
     * add2 使用已存在的事务，rollback
     */
    void transactionNestedNestedException2();

    /**
     * 清空表数据
     */
    void truncateTable();

}
