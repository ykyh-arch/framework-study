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
public class ConsumerOne {

    static int i = 0;

    public static void main(String[] args) {

        try {
            Connection connection = ConnectionUtil.getConnection();
            //通过链接得到Channel
            final Channel channel = connection.createChannel();
            channel.queueDeclare("queue1",true,false,false,null);
            //消息回调
            DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    i++;
                    if(i%2500==0 || i==10000){
                        //批量确认
                        channel.basicAck(envelope.getDeliveryTag(),true);
                        System.out.println(new String(body, "UTF-8"));
                    }
                }
            };
//            channel.basicConsume("queue1", true,deliverCallback);
            channel.basicQos(2500);
            channel.basicConsume("queue2", false,deliverCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
