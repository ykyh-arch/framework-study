package example.rabbitmq.cluster.strategy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName: Producer
 * @Description: 生产者。
 * @Author: Uetec
 * @Date: 2020-12-15-10:57
 * @Version: 1.0
 **/
public class Producer {
    public static void main(String[] args) throws Exception {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish("","rabbitmq1_queue",null,"hello1 world".getBytes());
        channel.basicPublish("","rabbitmq2_queue",null,"hello2 world".getBytes());
        channel.close();
        connection.close();
    }

    public static Connection getConnection() throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.48");
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
