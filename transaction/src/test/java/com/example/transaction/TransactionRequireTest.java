package com.example.transaction;

import com.example.transaction.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2015:51
 */
@SpringBootTest(classes =  TransactionApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TransactionRequireTest {

    @Autowired
    private PersonService targetService;

    @BeforeEach
    public void before(){
        targetService.truncateTable();
    }

    /**
     * 添加数据
     */
    @Test
    public void noTransactionRequiredRequired() {
        targetService.noTransactionRequiredRequired();
    }

    /**
     * 添加数据
     */
    @Test
    public void noTransactionRequiredRequiredException() {
        targetService.noTransactionRequiredRequiredException();
    }

    /**
     * 添加数据
     */
    @Test
    public void transactionRequiredRequired() {
        targetService.transactionRequiredRequired();
    }

    /**
     * 添加数据
     */
    @Test
    public void TransactionRequiredRequiredException() {
        targetService.transactionRequiredRequiredException();
    }


    /**
     * 添加数据
     */
    @Test
    public void TransactionRequiredRequiredException2() {
        targetService.transactionRequiredRequiredException2();
    }
}
