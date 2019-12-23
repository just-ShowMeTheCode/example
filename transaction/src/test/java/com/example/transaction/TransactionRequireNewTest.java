package com.example.transaction;

import com.example.transaction.service.PersonServiceRequiredNew;
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
public class TransactionRequireNewTest {

    @Autowired
    private PersonServiceRequiredNew targetService;

    @BeforeEach
    public void before(){
        targetService.truncateTable();
    }

    /**
     * 添加数据
     */
    @Test
    public void noTransactionRequiredRequired() {
        targetService.noTransactionRequiredNewRequiredNew();
    }

    /**
     * 添加数据
     */
    @Test
    public void noTransactionRequiredRequiredException() {
        targetService.noTransactionRequiredNewRequiredNewException();
    }

    /**
     * 添加数据
     */
    @Test
    public void TransactionRequiredNewRequiredNew() {
        targetService.transactionRequiredNewRequiredNew();
    }

    /**
     * 添加数据
     */
    @Test
    public void TransactionRequiredNewRequiredNewException() {
        targetService.transactionRequiredNewRequiredNewException();
    }


    /**
     * 添加数据
     */
    @Test
    public void TransactionRequiredNewRequiredNewException2() {
        targetService.transactionRequiredNewRequiredNewException2();
    }
}
