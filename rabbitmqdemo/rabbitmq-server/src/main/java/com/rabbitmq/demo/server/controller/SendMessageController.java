package com.rabbitmq.demo.server.controller;

import cn.hutool.core.util.RandomUtil;
import com.google.gson.Gson;
import com.rabbit.demo.common.bean.MessageStatusEnum;
import com.rabbit.demo.common.bean.OrderBillingDto;
import com.rabbit.demo.common.util.IdUtil;
import com.rabbitmq.demo.server.config.ExchangeMQConfig;
import com.rabbitmq.demo.server.model.MsgLog;
import com.rabbitmq.demo.server.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/4/1614:35
 */
@RestController
@RequestMapping("/produceor")
@Slf4j
public class SendMessageController {


    @Autowired
    private MsgLogService messageService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public ResponseEntity sendMessage(@RequestParam("messageCount") Integer  messageCount){
        List<OrderBillingDto> billingList = createOrderBillingList(messageCount);
        for (OrderBillingDto dto : billingList) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(String.valueOf(IdUtil.getTraceId()));
            rabbitTemplate.convertAndSend(ExchangeMQConfig.getExchange(),
                            ExchangeMQConfig.getRoutingKey(),
                    MessageBuilder.withBody(new Gson().toJson(dto).getBytes()).build(),correlationData);
        }

        return ResponseEntity.ok("success");
    }

    @GetMapping("/prioritySendMessage")
    public ResponseEntity prioritySendMessage(@RequestParam("messageCount") Integer  messageCount){
        List<OrderBillingDto> billingList = createOrderBillingList(messageCount);
        MessageProperties properties = new MessageProperties();
        for (OrderBillingDto dto : billingList) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dto.setTradeTime(LocalDateTime.now(ZoneId.systemDefault()));
            properties.setPriority(RandomUtil.randomInt(1,10));
            Message build = new Message(new Gson().toJson(dto).getBytes(),properties);
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(String.valueOf(IdUtil.getTraceId()));
            rabbitTemplate.convertAndSend(ExchangeMQConfig.getExchange(),
                    ExchangeMQConfig.getRoutingKey(),build
                    ,correlationData);
        }

        return ResponseEntity.ok("success");
    }

    @GetMapping("/send")
    public ResponseEntity send(@RequestParam("messageCount") Integer  messageCount,
                               @RequestParam("sleep")Integer sleep){
        List<OrderBillingDto> personList = createOrderBillingList(messageCount);
        for (OrderBillingDto dto : personList) {
            if(sleep != null && sleep > 0){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            CorrelationData correlationData =
                    new CorrelationData(String.valueOf(dto.getTransactionSerialNumber()));
            rabbitTemplate.convertAndSend(ExchangeMQConfig.getExchange(),
                    ExchangeMQConfig.getRoutingKey(),
                    MessageBuilder.withBody(new Gson().toJson(dto).getBytes()).build(),correlationData);
        }

        return ResponseEntity.ok("success");
    }


    @GetMapping("/sendMessage2")
    public ResponseEntity sendMessage2(){
        for (int i = 0; i < 20; i++) {
//            rabbitTemplate.send(queue,
//                    MessageBuilder.withBody(String.valueOf(i).getBytes()).build());
        }

        return ResponseEntity.ok("success");
    }


    @GetMapping("/sendMessage3")
    public ResponseEntity sendMessage3(){
        for (int i = 0; i < 20; i++) {

        }

        return ResponseEntity.ok("success");
    }

    private List<OrderBillingDto> createOrderBillingList(Integer messageCount){
        List<OrderBillingDto> list = new ArrayList<>();
        List<MsgLog> msgLogList = new ArrayList<>();
        for (int i = 0; i < messageCount; i++) {
            OrderBillingDto dto = new OrderBillingDto();
            dto.setOrderNo("order-" + i);
            dto.setServeNo("server-" + i);
            dto.setTransactionSerialNumber(String.valueOf(IdUtil.getTraceId()));
            dto.setTradeTime(LocalDateTime.now(ZoneId.systemDefault()));
            MsgLog msgLog = new MsgLog();
            msgLog.setMsg(new Gson().toJson(dto))
                    .setExchange(ExchangeMQConfig.getExchange())
                    .setRoutingKey(ExchangeMQConfig.getQueue())
                    .setMsgId(dto.getTransactionSerialNumber())
                    .setStatus(MessageStatusEnum.STATE1.getCode())
                    .setTryCount(0);

            list.add(dto);
            msgLogList.add(msgLog);
        }
        messageService.saveBatch(msgLogList);
        return list;
    }
}
