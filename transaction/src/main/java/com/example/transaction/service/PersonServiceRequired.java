package com.example.transaction.service;

import com.example.transaction.model.Person;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fumj
 * @since 2019-12-17
 */
public interface PersonServiceRequired extends IService<Person> {
    /**
     * 无事务控制，add1 和 add2 都insert 成功
     */
    void noTransactionRequiredRequired();
    /**
     * 无事务控制，
     * add1 success
     * add2 fail，rollback
     * add3 不执行
     */
    void noTransactionRequiredRequiredException();
    /**
     * 事务控制，add1 和 add2 都insert 成功
     */
    void transactionRequiredRequired();
    /**
     * 创建一个事务
     * add1 rollback
     * add2 rollback
     * add3 未执行
     */
    void transactionRequiredRequiredException();

    /**
     * 创建一个事务
     * add1 rollback
     * add2 rollback
     */
    void transactionRequiredRequiredException2();

    /**
     * 清空表数据
     */
    void truncateTable();


}
