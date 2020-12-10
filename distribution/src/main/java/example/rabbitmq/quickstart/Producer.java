package example.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import example.rabbitmq.utils.ConnectionUtil;

/**
 * @ClassName: Producer
 * @Description: 生产者。
 * @Author: Uetec
 * @Date: 2020-12-07-17:18
 * @Version: 1.0
 **/
public class Producer {
    public static void main(String[] args) {
        //创建队列，需要连接
        try {
            Connection connection = ConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
           /* //申明队列，参数：队列名称、持久化、排他性、自动删除、参数
            channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
            //交换机、路由键、消息属性、消息
            channel.basicPublish("",ConnectionUtil.QUEUE_NAME,null,"hello".getBytes());*/

            //测试
//            channel.queueDeclare("queue2",true,false,false,null);
//            channel.basicPublish("","queue2",null,"hello".getBytes());
            for (int i = 0; i < 10000; i++) {
                channel.basicPublish("","queue2",null,("hello "+(i+1)).getBytes());
            }

            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
