package com.example.transaction.service.impl;

import com.example.transaction.model.Person;
import com.example.transaction.dao.PersonMapper;
import com.example.transaction.service.PersonServiceRequired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fumj
 * @since 2019-12-17
 */
@Service
@Slf4j
public class PersonServiceRequiredImpl extends ServiceImpl<PersonMapper, Person> implements PersonServiceRequired {


    @Override
    public void noTransactionRequiredRequired() {
        PersonServiceRequiredImpl currentProxy = (PersonServiceRequiredImpl)AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2(person);
    }

    @Override
    public void noTransactionRequiredRequiredException() {
        PersonServiceRequiredImpl currentProxy = (PersonServiceRequiredImpl)AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2Exception(person);
        currentProxy.add3(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredRequired() {
        PersonServiceRequiredImpl currentProxy = (PersonServiceRequiredImpl)AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredRequiredException() {
        PersonServiceRequiredImpl currentProxy = (PersonServiceRequiredImpl)AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2Exception(person);
        currentProxy.add3(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredRequiredException2() {
        PersonServiceRequiredImpl currentProxy = (PersonServiceRequiredImpl)AopContext.currentProxy();
        Person person = getPerson();
        currentProxy.add1(person);
        currentProxy.add2(person);
        throw new RuntimeException("run time exception");
    }

    private Person getPerson() {
        Person person = new Person();
        person.setAge(33);
        return person;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void add1(Person person) {
        log.info("add1 person");
        person.setName("name1");
        this.baseMapper.insert(person);

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

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void add3(Person person) {
        log.info("add3 person");
        person.setName("name3");
        this.baseMapper.insert(person);
    }


    @Override
    public void truncateTable() {
        this.baseMapper.truncateTable();
    }
}
