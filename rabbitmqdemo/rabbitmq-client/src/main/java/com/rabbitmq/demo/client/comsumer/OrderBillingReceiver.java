package com.rabbitmq.demo.client.comsumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * @author fumj
 * @projectName example
 * @description: 消费队列
 * @date 2020/4/1618:00
 */
//@Component
@Slf4j
//@EnableRabbit
public class OrderBillingReceiver {


//    @RabbitListener(queues = "${mq.queue}")
//    @RabbitHandler
//    public void process(String message){
//        log.info("receive message:{}",message);
//    }

    @RabbitListener(queues = "${mq.queue}",concurrency = "1")
    @RabbitHandler
    public void process(String obj, Channel channel, Message message) throws IOException {
        try {
            Thread.sleep(5000);
            log.info("（1）  : " + obj);
//            if(obj.contains("order")){
//                throw new Exception("error");
//            }
            // 业务处理
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }


    @RabbitListener(queues = "${mq.queue}",concurrency = "1")
    @RabbitHandler
    public void process2(String obj, Channel channel, Message message) throws IOException {
        try {
            Thread.sleep(1000);
            log.info("（2）  : " + obj);
//            if(obj.contains("order")){
//                throw new Exception("error");
//            }
            // 业务处理
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
