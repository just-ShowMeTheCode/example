package com.example.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transaction.dao.PersonMapper;
import com.example.transaction.model.Person;
import com.example.transaction.service.TransactionTryCatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2315:47
 */
@Slf4j
@Service
public class TransactionTryCatchImpl extends ServiceImpl<PersonMapper, Person> implements TransactionTryCatch {
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void testTransactionTryCatchRequired() {
        TransactionTryCatchImpl currentProxy = (TransactionTryCatchImpl) AopContext.currentProxy();
        Person person = getPerson();
        try {
            currentProxy.add2Exception(person);
        }catch (Exception e){
            currentProxy.add2(person);
            throw new RuntimeException("run time exception");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void testTransactionTryCatchRequiredNew() {
        TransactionTryCatchImpl currentProxy = (TransactionTryCatchImpl) AopContext.currentProxy();
        Person person = getPerson();
        try {
            currentProxy.add1Exception(person);
        }catch (Exception e){
            currentProxy.add1(person);
            throw new RuntimeException("run time exception");
        }
    }

    @Override
    public void truncateTable() {
        this.baseMapper.truncateTable();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = RuntimeException.class)
    public void add1(Person person) {
        log.info("add1 person");
        person.setName("name1");
        this.baseMapper.insert(person);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = RuntimeException.class)
    public void add1Exception(Person person) {
        log.info("add1 person");
        person.setName("name1Exception");
        this.baseMapper.insert(person);
        int i = 1/0;

    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void add2(Person person) {
        log.info("add2 person");
        person.setName("name2");
        this.baseMapper.insert(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void add2Exception(Person person) {
        log.info("add2Exception person");
        person.setName("name2Exception");
        this.baseMapper.insert(person);
        int i = 1/0;
    }

    private Person getPerson() {
        Person person = new Person();
        person.setAge(33);
        return person;
    }




}
