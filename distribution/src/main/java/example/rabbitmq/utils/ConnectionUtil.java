package example.rabbitmq.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName: ConnectionUtil
 * @Description: 连接类。
 * @Author: Uetec
 * @Date: 2020-12-07-16:53
 * @Version: 1.0
 **/
public class ConnectionUtil {

    public static final String QUEUE_NAME = "queue1";

    public static final String EXCHANGE_NAME = "exchange1";

    public static Connection getConnection(){
        try {
            //创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            factory.setPort(5672);
            factory.setUsername("distribution");
            factory.setPassword("distribution");
            //VirtualHost 设置
            factory.setVirtualHost("/distribution");
            //一次TCP链接，底层Socket
            Connection connection = factory.newConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
