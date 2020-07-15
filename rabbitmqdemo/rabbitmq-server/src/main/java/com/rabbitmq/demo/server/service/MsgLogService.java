package com.rabbitmq.demo.server.service;

import com.rabbit.demo.common.enums.CodeEnum;
import com.rabbitmq.demo.server.model.MsgLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 消息投递日志 服务类
 * </p>
 *
 * @author fumj
 * @since 2020-07-08
 */
public interface MsgLogService extends IService<MsgLog> {

    void updateMsgLogStatus(String id, CodeEnum codeEnum);

    List<MsgLog> selectResendMsg();
}
