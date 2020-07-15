package com.rabbitmq.demo.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.demo.common.bean.MessageStatusEnum;
import com.rabbit.demo.common.enums.CodeEnum;
import com.rabbitmq.demo.server.dao.MsgLogMapper;
import com.rabbitmq.demo.server.model.MsgLog;
import com.rabbitmq.demo.server.service.MsgLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 消息投递日志 服务实现类
 * </p>
 *
 * @author fumj
 * @since 2020-07-08
 */
@Service
public class MsgLogServiceImpl extends ServiceImpl<MsgLogMapper, MsgLog> implements MsgLogService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void updateMsgLogStatus(String id, CodeEnum codeEnum){
        MsgLog byId = this.getById(id);
        if(byId != null){
            byId.setStatus(codeEnum.getCode());
            baseMapper.updateById(byId);
        }
    }

    @Override
    public List<MsgLog> selectResendMsg() {
        LambdaQueryWrapper<MsgLog> lambda = new QueryWrapper<MsgLog>().lambda();
        lambda.eq(MsgLog::getStatus, MessageStatusEnum.STATE3.getCode());
        lambda.le(MsgLog::getTryCount,3);
        return null;
    }
}
