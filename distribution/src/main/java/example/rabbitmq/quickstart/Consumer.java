package example.rabbitmq.quickstart;

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
public class Consumer {

    public static void main(String[] args) {

        try {
            Connection connection = ConnectionUtil.getConnection();
            //通过链接得到Channel
            final Channel channel = connection.createChannel();
            channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
            //消息回调
            DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    System.out.println(new String(body, "UTF-8"));
                    System.out.println("消息消费成功");
                    //multiple：批量确认，手动确认，目的：为了防止消息丢失
                    //消息重复消费，如何解决？？方案：利用业务字段来标识
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            };
            //开始消费，autoAck：当true自动确认，消息系统就不记录，默认false，即为手动确认
//            channel.basicConsume(ConnectionUtil.QUEUE_NAME, true,deliverCallback);
            channel.basicConsume(ConnectionUtil.QUEUE_NAME, deliverCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
