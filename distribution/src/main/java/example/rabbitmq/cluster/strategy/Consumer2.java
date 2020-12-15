package example.rabbitmq.cluster.strategy;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Consumer1
 * @Description: 消费者2号。
 * @Author: Uetec
 * @Date: 2020-12-15-10:44
 * @Version: 1.0
 **/
public class Consumer2 {

    public static void main(String[] args) throws Exception {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        //镜像模式
//        Map<String,Object> param = new HashMap<String, Object>();
//        param.put("x-ha-policy","nodes");
//        param.put("x-ha-nodes","[rabbitmq@rabbitmq1]");
        channel.queueDeclare("rabbitmq2_queue",true,false,false,null);
        //消息回调
        DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                    System.out.println(new String(body, "UTF-8"));
            }
        };
        channel.basicConsume("rabbitmq2_queue", true,deliverCallback);
    }

    public static Connection getConnection() throws Exception {
            //创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.1.49");
            factory.setPort(5672);
            factory.setUsername("templation");
            factory.setPassword("123456");
            //VirtualHost 设置
            factory.setVirtualHost("/");
            //超时配置
            factory.setHandshakeTimeout(10000);
            factory.setChannelRpcTimeout(10000);
            factory.setConnectionTimeout(10000);
            factory.setShutdownTimeout(10000);
            //一次TCP链接，底层Socket
            Connection connection = factory.newConnection();
            return connection;
    }

}
