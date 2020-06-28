package com.rabbitmq.demo.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeMQConfig {

    @Value("${mq.exchange}")
    private String exchange;

    @Value("${mq.queue}")
    private String queue;

    @Bean
    public DirectExchange exchange() {

        return new DirectExchange(exchange,true,false);
    }

    @Bean
    public Queue queue() {
        return new Queue(queue,true);
    }

    @Bean
    public Binding ingateQueueBinding() {
        return BindingBuilder.bind(queue()).to(exchange()).withQueueName();
    }


}
