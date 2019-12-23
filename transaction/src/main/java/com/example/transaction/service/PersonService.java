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
public interface PersonService extends IService<Person> {
    /**
     * 无事务控制，add1 和 add2 都insert 成功
     */
    void noTransactionRequiredRequired();
    /**
     * 无事务控制，add1 和 add2 都insert 成功，但add2 执行报错，无事务，不回滚
     */
    void noTransactionRequiredRequiredException();
    /**
     * 事务控制，add1 和 add2 都insert 成功
     */
    void transactionRequiredRequired();
    /**
     * 事务控制，add1 和 add2 都insert 失败，事务回滚
     */
    void transactionRequiredRequiredException();

    /**
     * 事务控制，add1 和 add2 都insert 失败，事务回滚
     */
    void transactionRequiredRequiredException2();

    /**
     * 清空表数据
     */
    void truncateTable();


}
