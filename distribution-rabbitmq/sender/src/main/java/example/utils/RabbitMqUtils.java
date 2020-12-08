package example.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RabbitMqUtils
 * @Description: rabbitmq工具类。
 * @Author: Uetec
 * @Date: 2020-12-08-16:45
 * @Version: 1.0
 **/
@Component
public class RabbitMqUtils {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange,String routingKey,String message) {
        //发送消息
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}

