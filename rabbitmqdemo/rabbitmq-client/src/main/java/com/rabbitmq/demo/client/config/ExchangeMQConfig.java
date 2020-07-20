package com.rabbitmq.demo.client.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ExchangeMQConfig {

    public static String QUEUE;

    @Value("${mq.queue}")
    public void setQUEUE(String QUEUE) {
        ExchangeMQConfig.QUEUE = QUEUE;
    }

    public String getQUEUE() {
        return ExchangeMQConfig.QUEUE;
    }


    /**
     * Direct 模式交换机 Exchange
     */
    @Bean
    public Queue queue() {

//        return new Queue(QUEUE, true);

        Map<String, Object> args = new HashMap<>();
        args.put("x-max-priority",10); // 优先级
        args.put("x-message-ttl", 5000); //消息10秒过期。
        args.put("x-dead-letter-exchange", "dlx.b.ex");// 过期后,或者消费失败如果没有消费这条消费，则将消息转入到dlx交换机中去
        args.put("x-dead-letter-routing-key", "dlx.b.key");//转入到dlx交换机，路由规则为dlx.b.key
        Queue q = new Queue(QUEUE, true, false, false, args);
        return q;
    }

}
