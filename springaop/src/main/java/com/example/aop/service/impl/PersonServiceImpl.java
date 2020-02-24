package com.example.aop.service.impl;

import com.example.aop.model.Person;
import com.example.aop.dao.PersonMapper;
import com.example.aop.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fumj
 * @since 2020-02-18
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
