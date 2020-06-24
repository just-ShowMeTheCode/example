package com.rabbitmq.demo.server.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeMQConfig {

    @Value("${mq.exchange}")
    private String exchange;

    @Value("${mq.queue}")
    private String ingateQueue;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange,true,false);
    }

    @Bean
    public Queue queue() {
        return new Queue(ingateQueue,true);
    }

    @Bean
    public Binding ingateQueueBinding() {
        return BindingBuilder.bind(queue()).to(exchange()).withQueueName();
    }

    /**
     * 配置启用rabbitmq事务
     * @param connectionFactory
     * @return
     */
    @Bean("rabbitTransactionManager")
    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }

}
