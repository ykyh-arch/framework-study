package example.utils;

import com.rabbitmq.client.Channel;
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

    @RabbitListener(queues = "queue2",containerFactory = "simpleRabbitListenerContainerFactory")
    public void getMessage(Message message, Channel channel) throws Exception{
        System.out.println("消费者1号");
        if(operStock()){
            //确认消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }else{
            //消息退回，批量退回
            System.out.println("消息已退回");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            //单条退回，标识、是否重回队列
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }

        System.out.println(new String(message.getBody(),"UTF-8"));
    }

    //减库存
    public boolean operStock(){
        return false;
    }

    @RabbitListener(queues = "queue2",containerFactory = "simpleRabbitListenerContainerFactory")
    public void get2Msg(Message message, Channel channel) throws Exception{
        System.out.println("消费者2号");
        //确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println(new String(message.getBody(),"UTF-8"));
    }

    @RabbitListener(queues = "queue4",containerFactory = "simpleRabbitListenerContainerFactory")
    public void get4Msg(Message message, Channel channel) throws Exception{
        System.out.println("消费者4号");
        //拒绝消息，不重回队列
        channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        System.out.println("消费者4号拒绝消息");
        System.out.println(new String(message.getBody(),"UTF-8"));
    }

    @RabbitListener(queues = "queue5",containerFactory = "simpleRabbitListenerContainerFactory")
    public void get5Msg(Message message, Channel channel) throws Exception{
        System.out.println("消费者5号");
        //确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println("消费者5号获得消息");
        System.out.println(new String(message.getBody(),"UTF-8"));
    }

}

