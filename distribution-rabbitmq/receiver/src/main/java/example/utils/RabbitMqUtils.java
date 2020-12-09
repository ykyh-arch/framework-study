package example.utils;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    /**
     * 如果消费者监听同一个队列消息，默认以轮询方式处理消息
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "queue1")
    public void get(String message) throws Exception{
        System.out.println("消费者1号");
        System.out.println(message);
    }

    @RabbitListener(queues = "queue1")
    public void getMessage(Message message) throws Exception{
        System.out.println("消费者2号");
        System.out.println(new String(message.getBody(),"UTF-8"));
    }

}

