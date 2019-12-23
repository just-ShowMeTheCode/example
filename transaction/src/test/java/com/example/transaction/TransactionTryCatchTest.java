package com.example.transaction;

import com.example.transaction.service.PersonServiceRequired;
import com.example.transaction.service.TransactionTryCatch;
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
public class TransactionTryCatchTest {

    @Autowired
    private TransactionTryCatch targetService;

    @BeforeEach
    public void before(){
        targetService.truncateTable();
    }

    /**
     * 添加数据
     */
    @Test
    public void testTransactionTryCatchRequired() {
        targetService.testTransactionTryCatchRequired();
    }

    /**
     * 添加数据
     */
    @Test
    public void testTransactionTryCatchRequiredNew() {
        targetService.testTransactionTryCatchRequiredNew();
    }


}
