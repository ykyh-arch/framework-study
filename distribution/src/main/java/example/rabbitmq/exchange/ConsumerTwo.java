package example.rabbitmq.exchange;

import com.rabbitmq.client.*;
import example.rabbitmq.utils.ConnectionUtil;

import java.io.IOException;

/**
 * @ClassName: Consumer
 * @Description: 消费者。
 * @Author: Uetec
 * @Date: 2020-12-07-17:42
 * @Version: 1.0
 **/
public class ConsumerTwo {

    public static void main(String[] args) {

        try {
            Connection connection = ConnectionUtil.getConnection();
            //通过链接得到Channel
            final Channel channel = connection.createChannel();
            channel.queueDeclare("queue2",true,false,false,null);
            //消息回调
            DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    try {
                        //模拟消费者性能慢
                        Thread.sleep(500);
                        channel.basicAck(envelope.getDeliveryTag(),false);
                        System.out.println(new String(body, "UTF-8"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            //消息预取，默认Mq系统将队列消息按轮询机制一次性全发送给客户端
            //通过设置消息预取值，可以控制Mq一次发送消息的数量，该参数需要配合手动确认方式，
            //这样使得性能好的客户端处理完消息可以帮助其他客户端处理消息
            channel.basicQos(1);
            channel.basicConsume("queue2", false,deliverCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
