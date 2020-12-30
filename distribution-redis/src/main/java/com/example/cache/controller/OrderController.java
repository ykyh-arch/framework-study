package com.example.cache.controller;

import com.example.cache.entity.Order;
import com.example.cache.entity.CommonResult;
import com.example.cache.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/insert")
    public Integer insertOrder(Order order){
        return orderService.insertOrder(order);
    }


    /**
     * @param id
     * @return
     * 测试：http://192.168.1.175:8080//selectById?id=3 结果：{"code":600,"data":null,"msg":"查询无果"}
     */
    @GetMapping("/selectById")
    public CommonResult selectOrderById(Integer id){
        return orderService.selectOrderById(id);
    }
}
