package org.example.service;

import org.springframework.stereotype.Service;

/**
 * @ClassName: UserService
 * @Description: 用户服务。
 * @Author: Uetec
 * @Date: 2021-01-19-16:29
 * @Version: 1.0
 **/
@Service
public class UserService {

    private OrderService orderService;

    public OrderService getOrderService() {
        return orderService;
    }

    //根据类型by_type自动装配
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
