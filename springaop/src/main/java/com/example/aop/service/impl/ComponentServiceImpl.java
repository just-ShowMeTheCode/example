package com.example.aop.service.impl;

import com.example.aop.model.Component;
import com.example.aop.dao.ComponentMapper;
import com.example.aop.service.ComponentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 零件表 服务实现类
 * </p>
 *
 * @author fumj
 * @since 2020-02-13
 */
@Service
public class ComponentServiceImpl extends ServiceImpl<ComponentMapper, Component> implements ComponentService {


    @Override
    public void findById(Long id) {

    }
}
