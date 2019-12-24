package com.example.transaction;

import com.example.transaction.service.PersonService1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/249:39
 */
@Slf4j
@SpringBootTest(classes =  TransactionApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonService1ImplTest  {

    @Autowired
    private PersonService1 targetService;

    @BeforeEach
    public void before(){
        targetService.truncateTable();
    }

   @Test
    public void transactionRequiredRequired() {
       targetService.transactionRequiredRequired();
    }

    @Test
    public void transactionRequiredRequiredException() {
        targetService.transactionRequiredRequiredException();
    }

    @Test
    public void transactionRequiredRequiredExceptionCatch() {
        targetService.transactionRequiredRequiredExceptionCatch();
    }

    @Test
    public void transactionRequiredRequiredNew() {
        targetService.transactionRequiredRequiredNew();
    }

    @Test
    public void transactionRequiredNested() {
        targetService.transactionRequiredNested();
    }

    @Test
    public void transactionRequiredNestedException() {
        targetService.transactionRequiredNestedExceptionCatch();
    }

}
