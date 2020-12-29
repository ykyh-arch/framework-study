package com.example.cache.init;

import com.example.cache.entity.Order;
import com.example.cache.filter.RedisBloomFilter;
import com.example.cache.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RedisDataInit {

    @Autowired
    OrderService orderService;

    @Autowired
    RedisBloomFilter redisBloomFilter;

    @PostConstruct
    public void init(){
        List<Order> orders = orderService.selectOrderyAll();
        for (Order order : orders) {
            redisBloomFilter.put(String.valueOf(order.getId()));
        }
    }
}
