package example.controller;

import example.utils.RabbitMqUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OrderController
 * @Description: 订单。
 * @Author: Uetec
 * @Date: 2020-12-08-16:45
 * @Version: 1.0
 **/
@RestController
public class OrderController {

    @Autowired
    RabbitMqUtils rabbitMqUtils;

    /**
     * 创建订单 192.168.1.175:8081/order.do?exchange=exchange1&routingKey=debug.user.b&message=123
     * @param exchange
     * @param routingKey
     * @param message
     * @return
     */
    @RequestMapping({"/order.do"})
    public String createOrder(String exchange,String routingKey,String message) {
        rabbitMqUtils.sendMessage(exchange,routingKey,message);
        return "下订单成功";
    }

}
