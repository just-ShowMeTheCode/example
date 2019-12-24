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
public interface PersonService2 extends IService<Person> {

    /**
     */
    void transactionRequired();

    /**
     */
    void transactionRequiredException();

    /**
     */
    void transactionRequiredExceptionCatch();

    /**
     */
    void transactionRequiredNew();

    /**
     */
    void transactionNested();

    /**
     */
    void transactionNestedException();

    /**
     */
    void truncateTable();

}
