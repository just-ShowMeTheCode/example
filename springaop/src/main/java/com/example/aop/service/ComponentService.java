package com.example.aop.service;

import com.example.aop.model.Component;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 零件表 服务类
 * </p>
 *
 * @author fumj
 * @since 2020-02-13
 */
public interface ComponentService extends IService<Component> {

    void findById(Long id);
}
