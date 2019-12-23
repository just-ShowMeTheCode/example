package com.example.transaction.service.impl;

import com.example.transaction.model.Person;
import com.example.transaction.dao.PersonMapper;
import com.example.transaction.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {


    @Override
    public void noTransactionRequiredRequired() {
       Person person = getPerson();
        add1(person);
        add2(person);
    }


    @Override
    public void noTransactionRequiredRequiredException() {
        Person person = getPerson();
        add1(person);
        add2Exception(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredRequired() {
        Person person = getPerson();
        add1(person);
        add2(person);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Override
    public void transactionRequiredRequiredException() {
        Person person = getPerson();
        add1(person);
        add2Exception(person);
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
    @Override
    public void transactionRequiredRequiredException2() {
        Person person = getPerson();
        add1(person);
        add2(person);
        throw new RuntimeException("run time exception");
    }

    @Override
    public void truncateTable() {
        this.baseMapper.truncateTable();
    }
}
