package com.rabbitmq.demo.server.controller;

import com.google.gson.Gson;
import com.rabbit.demo.common.bean.OrderBillingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Value("${spring.rabbitmq.queue.orderbilling}")
    private String queue;


    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public ResponseEntity sendMessage(){
        List<OrderBillingDto> billingList = createOrderBillingList();
        for (OrderBillingDto dto : billingList) {
            rabbitTemplate.send(queue,
                    MessageBuilder.withBody(new Gson().toJson(dto).getBytes()).build());
        }

        return ResponseEntity.ok("success");
    }


    @GetMapping("/sendMessage2")
    public ResponseEntity sendMessage2(){
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.send(queue,
                    MessageBuilder.withBody(String.valueOf(i).getBytes()).build());
        }

        return ResponseEntity.ok("success");
    }


    @GetMapping("/sendMessage3")
    public ResponseEntity sendMessage3(){
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.send(queue,
                    MessageBuilder.withBody(String.valueOf(i).getBytes()).build());
        }

        return ResponseEntity.ok("success");
    }

    private List<OrderBillingDto> createOrderBillingList(){
        List<OrderBillingDto> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            OrderBillingDto dto = new OrderBillingDto();
            dto.setOrderNo("order-" + i);
//            dto.setServeNo("server-" + i);
//            dto.setTransactionSerialNumber(String.valueOf(IdUtil.getTraceId()));
//            dto.setTradeTime(LocalDateTime.now(ZoneId.systemDefault()));
            list.add(dto);
        }
        return list;
    }
}
