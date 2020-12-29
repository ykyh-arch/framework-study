package com.example.cache.service;

import com.example.cache.entity.Order;
import com.example.cache.entity.CommonResult;

import java.util.List;

public interface OrderService {
    Integer insertOrder(Order order);

    CommonResult selectOrderById(Integer id);

    List<Order> selectOrderyAll();

    CommonResult synchronizedSelectOrderById(Integer id);
}
