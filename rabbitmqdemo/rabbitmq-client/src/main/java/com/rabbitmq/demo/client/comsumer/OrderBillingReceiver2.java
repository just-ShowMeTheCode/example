package com.rabbitmq.demo.client.comsumer;

import com.google.gson.Gson;
import com.rabbit.demo.common.bean.MessageStatusEnum;
import com.rabbit.demo.common.bean.OrderBillingDto;
import com.rabbitmq.client.Channel;
import com.rabbitmq.demo.client.model.MsgLog;
import com.rabbitmq.demo.client.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author fumj
 * @projectName example
 * @description: 消费队列
 * @date 2020/4/1618:00
 */
@Component
@Slf4j
@EnableRabbit
public class OrderBillingReceiver2 {


    @Autowired
    private MsgLogService msgLogService;

    @RabbitListener(queues = "${mq.queue}",concurrency = "1")
    @RabbitHandler
    public void process(Channel channel, Message message) throws IOException {
        try {
            OrderBillingDto dto = new Gson()
                    .fromJson(new String(message.getBody()),OrderBillingDto.class);

            log.info("（1）  : " + dto.getOrderNo());
            MsgLog msgLog = msgLogService.getById(dto.getTransactionSerialNumber());
            if(msgLog == null || msgLog.getStatus().equals(MessageStatusEnum.STATE2.getCode())){
                log.info("重复消费, msgId: {}", msgLog.getMsgId());
                return;
            }

//            double d = 1 / 0;

            // 模拟业务处理
            Thread.sleep(1000);
            // 业务处理
            msgLog.setStatus(MessageStatusEnum.STATE2.getCode());
            msgLogService.updateById(msgLog);
            // 消息确认
            log.info("（1） deliverTag:{}",message.getMessageProperties().getDeliveryTag());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("消费失败 {}",e);
//            log.error("消费失败 {}");
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }


//    @RabbitListener(queues = "${mq.queue}",concurrency = "1")
//    @RabbitHandler
    public void process2(Channel channel, Message message) throws IOException {
        try {
            OrderBillingDto dto = new Gson()
                    .fromJson(new String(message.getBody()),OrderBillingDto.class);

            log.info("（2）  : " + dto.getOrderNo());
            MsgLog msgLog = msgLogService.getById(dto.getTransactionSerialNumber());
            if(msgLog == null || msgLog.getStatus().equals(MessageStatusEnum.STATE2.getCode())){
                log.info("重复消费, msgId: {}", msgLog.getMsgId());
                return;
            }
//            if(obj.contains("order")){
//                throw new Exception("error");
//            }
            // 业务处理
            msgLog.setStatus(MessageStatusEnum.STATE2.getCode());
            msgLogService.updateById(msgLog);
            // 消息确认
            log.info("（2） deliverTag:{}",message.getMessageProperties().getDeliveryTag());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
