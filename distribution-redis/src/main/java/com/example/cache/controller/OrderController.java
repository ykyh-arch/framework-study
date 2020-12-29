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


    @GetMapping("/selectById")
    public CommonResult selectOrderById(Integer id){
        return orderService.selectOrderById(id);
    }
}
