package client.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new Queue(QUEUE, true);
    }
}
