package example.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
        //发送消息，附加参数
        /*CorrelationData correlationData = new CorrelationData();
        correlationData.setId("订单ID：20201209-1025");
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);*/

        //发送对象
        Map<String,Object> data = new HashMap<>();
        data.put("id",1);
        data.put("name","xiaowu");
        data.put("age",18);
        //手动对象序列号
       //rabbitTemplate.convertAndSend("exchange1", "debug.user.b", JSON.toJSONString(data));
        //自定义对象转化器
//        rabbitTemplate.convertAndSend("exchange1", "debug.user.b", data);
        //死信交换机测试
        rabbitTemplate.convertAndSend("exchange2", "queue4.data", data);

    }

}

