package client.comsumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/4/1618:00
 */
@Component
@Slf4j
@EnableRabbit
public class OrderBillingReceiver {


    @RabbitListener(queues = "order-billing-queue")
    public void process(String queueMsg, Message message, Channel channel){

        try {
            log.info("receive message:{}", queueMsg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            log.error(e.getMessage());
            try {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
