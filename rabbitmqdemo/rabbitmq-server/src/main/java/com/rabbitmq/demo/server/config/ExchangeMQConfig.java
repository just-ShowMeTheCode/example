package com.rabbitmq.demo.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ExchangeMQConfig {

    private static String exchange;

    private static String queue;

    private static String routingKey;

    public static String getExchange() {
        return exchange;
    }

    @Value("${mq.exchange}")
    public void setExchange(String exchange) {
        ExchangeMQConfig.exchange = exchange;
    }

    public static String getQueue() {
        return queue;
    }

    @Value("${mq.queue}")
    public void setQueue(String queue) {
        ExchangeMQConfig.queue = queue;
    }

    public static String getRoutingKey() {
        return routingKey;
    }

    @Value("${mq.routeing-key}")
    public void setRoutingKey(String routingKey) {
        ExchangeMQConfig.routingKey = routingKey;
    }


    @Bean
    public DirectExchange exchange() {

        return new DirectExchange(exchange,true,false);
    }

    @Bean
    public Queue queue() {

        // 死信队列
        Map<String, Object> args = new HashMap<>();
        args.put("x-max-priority",10); // 优先级
        args.put("x-message-ttl", 50000); //消息10秒过期。
        args.put("x-dead-letter-exchange", "dlx.b.ex");// 过期后,或者消费失败如果没有消费这条消费，则将消息转入到dlx交换机中去
        args.put("x-dead-letter-routing-key", "dlx.b.key");//转入到dlx交换机，路由规则为dlx.b.key
        Queue q = new Queue(queue, true, false, false, args);
        return q;
    }

    @Bean
    public Binding ingateQueueBinding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }



}
