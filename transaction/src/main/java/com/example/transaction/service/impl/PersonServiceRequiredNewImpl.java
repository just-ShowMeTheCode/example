package com.example.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transaction.dao.PersonMapper;
import com.example.transaction.model.Person;
import com.example.transaction.service.PersonServiceRequiredNew;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2310:17
 */
@Slf4j
@Service
public class PersonServiceRequiredNewImpl extends ServiceImpl<PersonMapper, Person> implements PersonServiceRequiredNew {
    @Override
    public void noTransactionRequiredNewRequiredNew() {
        PersonServiceRequiredNewImpl currentProxy = (PersonServiceRequiredNewImpl) AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2(person);
    }

    @Override
    public void noTransactionRequiredNewRequiredNewException() {
        PersonServiceRequiredNewImpl currentProxy = (PersonServiceRequiredNewImpl)AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2Exception(person);
        currentProxy.add3(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredNewRequiredNew() {
        PersonServiceRequiredNewImpl currentProxy = (PersonServiceRequiredNewImpl)AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2(person);

    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredNewRequiredNewException() {
        PersonServiceRequiredNewImpl currentProxy = (PersonServiceRequiredNewImpl)AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2Exception(person);
        currentProxy.add3(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredNewRequiredNewException2() {
        PersonServiceRequiredNewImpl currentProxy = (PersonServiceRequiredNewImpl)AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2(person);
        throw new RuntimeException("run time exception");
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
    public void add2(Person person) {
        log.info("add2 person");
        person.setName("name2");
        this.baseMapper.insert(person);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = RuntimeException.class)
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


    public void add3(Person person) {
        log.info("add3 person");
        person.setName("name3");
        this.baseMapper.insert(person);
    }

}
