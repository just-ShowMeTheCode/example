package com.example.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transaction.dao.PersonMapper;
import com.example.transaction.model.Person;
import com.example.transaction.service.PersonService1;
import com.example.transaction.service.PersonService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/249:39
 */
@Slf4j
@Service
public class PersonService2Impl extends ServiceImpl<PersonMapper, Person> implements PersonService2 {


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequired() {
        log.info("PersonService2Impl transactionRequired");
        Person person = getPerson();
        add1(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredException() {
        log.info("PersonService2Impl transactionRequiredException");
        int i = 1/0;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredExceptionCatch() {
        log.info("PersonService2Impl transactionRequiredException");
        int i = 1/0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredNew() {
        log.info("PersonService2Impl transactionRequiredNew");
        Person person = getPerson();
        add1(person);
    }

    @Transactional(propagation = Propagation.NESTED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionNested() {
        log.info("PersonService2Impl transactionNested");
        Person person = getPerson();
        add1(person);
    }

    @Transactional(propagation = Propagation.NESTED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionNestedException() {
        log.info("PersonService2Impl transactionNestedException");
        int i = 1/0;
    }

    private Person getPerson() {
        Person person = new Person();
        person.setAge(33);
        return person;
    }

    public void add1(Person person) {
        log.info("PersonService2Impl add1 person");
        person.setName("PersonService2Impl name1");
        this.baseMapper.insert(person);

    }

    public void add2(Person person) {
        log.info("add2 person");
        person.setName("name2");
        this.baseMapper.insert(person);
    }


    public void add2Exception(Person person) {
        log.info("add2Exception person");
        person.setName("name2Exception");
        this.baseMapper.insert(person);
        int i = 1/0;
    }

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
