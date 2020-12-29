package com.example.cache.service.impl;

import com.example.cache.entity.Order;
import com.example.cache.entity.CommonResult;
import com.example.cache.mapper.OrderMapper;
import com.example.cache.service.OrderService;
import com.example.cache.template.CacheLoadble;
import com.example.cache.template.CacheTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

//@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ValueOperations valueOperations;

    @Autowired
    CacheTemplate cacheTemplate;


    @Override
    public Integer insertOrder(Order order) {
        Integer integer = orderMapper.insertOrder(order);
        return integer;
    }

    @Override
    public CommonResult selectOrderById(Integer id) {
        return cacheTemplate.redisFindCache(String.valueOf(id), 10, TimeUnit.MINUTES, new CacheLoadble<Order>() {
            @Override
            public Order load() {
                return orderMapper.selectOrderById(id);
            }
        },true);
    }

    public CommonResult synchronizedSelectOrderById(Integer id) {
        return cacheTemplate.findCache(String.valueOf(id), 10, TimeUnit.MINUTES, new CacheLoadble<Order>() {
            @Override
            public Order load() {
                return orderMapper.selectOrderById(id);
            }
        });
    }


    @Override
    public List<Order> selectOrderyAll() {
        return orderMapper.selectOrderyAll();
    }
}
