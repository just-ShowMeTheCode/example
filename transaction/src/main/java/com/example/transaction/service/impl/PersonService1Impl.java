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
public class PersonService1Impl extends ServiceImpl<PersonMapper, Person> implements PersonService1 {

    private final PersonService2 personService2;

    public PersonService1Impl(PersonService2 personService2) {
        this.personService2 = personService2;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredRequired() {
        Person person = getPerson();
        add1(person);
        personService2.transactionRequired();

    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredRequiredException() {
        Person person = getPerson();
        add1(person);
        personService2.transactionRequiredException();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredRequiredExceptionCatch() {
        Person person = getPerson();
        add1(person);
        try {
            personService2.transactionRequiredExceptionCatch();
        } catch (Exception e) {
            log.error("fail",e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredRequiredNew() {
        Person person = getPerson();
        add1(person);
        personService2.transactionRequiredNew();
    }

    @Transactional(propagation = Propagation.NESTED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredNested() {
        Person person = getPerson();
        add1(person);
        personService2.transactionNested();
    }

    @Override
    public void transactionRequiredNestedExceptionCatch() {
        Person person = getPerson();
        add1(person);
        try {
            personService2.transactionNestedException();
        } catch (Exception e) {
            log.error("fail",e);
        }
    }

    private Person getPerson() {
        Person person = new Person();
        person.setAge(33);
        return person;
    }

    public void add1(Person person) {
        log.info("add1 person");
        person.setName("name1");
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
