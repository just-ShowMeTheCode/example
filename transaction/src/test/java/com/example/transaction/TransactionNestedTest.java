package com.example.transaction;

import com.example.transaction.service.PersonServiceNested;
import com.example.transaction.service.PersonServiceNested;
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
public class TransactionNestedTest {

    @Autowired
    private PersonServiceNested targetService;

    @BeforeEach
    public void before(){
        targetService.truncateTable();
    }

    /**
     * 添加数据
     */
    @Test
    public void noTransactionNestedNested() {
        targetService.noTransactionNestedNested();
    }

    /**
     * 添加数据
     */
    @Test
    public void noTransactionNestedNestedException() {
        targetService.noTransactionNestedNestedException();
    }

    /**
     * 添加数据
     */
    @Test
    public void transactionNestedNested() {
        targetService.transactionNestedNested();
    }

    /**
     * 添加数据
     */
    @Test
    public void TransactionNestedNestedException() {
        targetService.transactionNestedNestedException();
    }


    /**
     * 添加数据
     */
    @Test
    public void TransactionNestedNestedException2() {
        targetService.transactionNestedNestedException2();
    }
}
