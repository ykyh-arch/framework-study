package example.rabbitmq.exchange;

import com.rabbitmq.client.BuiltinExchangeType;
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

            /*//申明交换机
            channel.exchangeDeclare(ConnectionUtil.EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            //申明交换机与队列绑定
            channel.queueBind("queue1",ConnectionUtil.EXCHANGE_NAME,"");
            channel.queueBind("queue2",ConnectionUtil.EXCHANGE_NAME,"");
            //channel.queueBind("queue3",ConnectionUtil.EXCHANGE_NAME,"");
            //交换机、路由键、消息属性、消息
            channel.basicPublish(ConnectionUtil.EXCHANGE_NAME,"",null,"hello".getBytes());*/

            /*channel.exchangeDelete("exchange1");
            channel.exchangeDeclare(ConnectionUtil.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            channel.queueBind("queue1",ConnectionUtil.EXCHANGE_NAME,"info.user");
            channel.queueBind("queue2",ConnectionUtil.EXCHANGE_NAME,"warn.user");
            channel.queueBind("queue3",ConnectionUtil.EXCHANGE_NAME,"debug.user");
            channel.basicPublish(ConnectionUtil.EXCHANGE_NAME,"info.user",null,"hello".getBytes());*/

            channel.exchangeDelete("exchange1");
            channel.exchangeDeclare(ConnectionUtil.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            channel.queueBind("queue1",ConnectionUtil.EXCHANGE_NAME,"debug.*.b");
            channel.queueBind("queue2",ConnectionUtil.EXCHANGE_NAME,"info.#");
            channel.queueBind("queue3",ConnectionUtil.EXCHANGE_NAME,"*.email.*");

            String[] string1 = new String[]{"debug","warn","info"};
            String[] string2 = new String[]{"user","email","order"};
            String[] string3 = new String[]{"a","b","c"};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3;j++) {
                    for (int k = 0; k < 3; k++) {
                        System.out.println(string1[i]+"."+string2[j]+"."+string3[k]);
                        String msg = string1[i]+"."+string2[j]+"."+string3[k];
                        channel.basicPublish(ConnectionUtil.EXCHANGE_NAME,msg,null,msg.getBytes());
                    }
                }
            }

            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
