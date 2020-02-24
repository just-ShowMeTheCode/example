package com.example.aop.service.impl;

import com.example.aop.model.ApiLog;
import com.example.aop.dao.ApiLogMapper;
import com.example.aop.service.ApiLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fumj
 * @since 2020-02-13
 */
@Service
public class ApiLogServiceImpl extends ServiceImpl<ApiLogMapper, ApiLog> implements ApiLogService {

}
