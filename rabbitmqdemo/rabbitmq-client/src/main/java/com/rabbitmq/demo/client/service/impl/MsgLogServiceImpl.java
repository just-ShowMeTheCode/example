package com.rabbitmq.demo.client.service.impl;

import com.rabbitmq.demo.client.model.MsgLog;
import com.rabbitmq.demo.client.dao.MsgLogMapper;
import com.rabbitmq.demo.client.service.MsgLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息投递日志 服务实现类
 * </p>
 *
 * @author fumj
 * @since 2020-07-09
 */
@Service
public class MsgLogServiceImpl extends ServiceImpl<MsgLogMapper, MsgLog> implements MsgLogService {

}
