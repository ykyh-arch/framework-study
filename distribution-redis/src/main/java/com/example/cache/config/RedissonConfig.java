package com.example.cache.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RedissonConfig
 * @Description: RedissonConfig 配置类。
 * @Author: Uetec
 * @Date: 2020-12-30-11:26
 * @Version: 1.0
 **/
@Configuration
public class RedissonConfig {

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://192.168.1.49:7000")
                .addNodeAddress("redis://192.168.1.49:7001")
                .addNodeAddress("redis://192.168.1.49:7002");
        Redisson redisson = (Redisson)Redisson.create(config);
        return redisson;
    }
}
