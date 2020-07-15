package com.rabbitmq.demo.server.config;

import com.rabbit.demo.common.bean.MessageStatusEnum;
import com.rabbitmq.demo.server.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/4/1619:26
 */
@Component
@Slf4j
public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MsgLogService msgLogService;

    @PostConstruct
    public void initRabbitTemplate(){
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
        rabbitTemplate.setMessageConverter(converter());
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            log.info("Success... 消息成功发送到交换机! correlationData:{}", correlationData);
        }else {
            log.info("Fail... 消息发送到交换机失败! correlationData:{}", correlationData);
            String id = correlationData.getId();
            msgLogService.updateMsgLogStatus(id, MessageStatusEnum.STATE3);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("Fail... message:{},从交换机exchange:{},以路由键routingKey:{}," +
                        "未找到匹配队列，replyCode:{},replyText:{}",
                message, exchange, routingKey, replyCode, replyText);

    }


}
