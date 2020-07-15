package com.rabbitmq.demo.server.job;

import com.google.gson.Gson;
import com.rabbit.demo.common.bean.MessageStatusEnum;
import com.rabbitmq.demo.server.config.ExchangeMQConfig;
import com.rabbitmq.demo.server.model.MsgLog;
import com.rabbitmq.demo.server.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/7/1015:47
 */
@Component
@Slf4j
public class ResendMsgJob {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MsgLogService msgLogService;

    private static final int MAX_TRY_COUNT = 3;
    @Scheduled(cron = "0/30 * * * * ?")
    public void resendMsg(){
        log.info("开始执行定时任务发送消息");
        List<MsgLog> msgLogs = msgLogService.selectResendMsg();
        msgLogs.forEach(k -> {
            String msgId = k.getMsgId();
            if(k.getTryCount() > MAX_TRY_COUNT){
                msgLogService.updateMsgLogStatus(msgId, MessageStatusEnum.STATE3);
                log.info("{}超过最大重试次数",msgId);
            }else {
                CorrelationData cd = new CorrelationData();
                cd.setId(k.getMsgId());
                rabbitTemplate.convertAndSend(ExchangeMQConfig.getQueue(),
                        MessageBuilder.withBody(new Gson().toJson(k).getBytes()).build(),
                        cd);
            }
        });
    }

}
