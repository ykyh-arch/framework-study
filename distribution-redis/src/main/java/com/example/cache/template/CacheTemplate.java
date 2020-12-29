package com.example.cache.template;

import com.example.cache.entity.NullValueResultDO;
import com.example.cache.entity.CommonResult;
import com.example.cache.filter.RedisBloomFilter;
import com.example.cache.util.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheTemplate<T> {

    @Autowired
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    RedisBloomFilter bloomFilter;


    @Autowired
    private RedisLock redisLock;



    public CommonResult findCache(String key, long expire, TimeUnit unit, CacheLoadble<T> cacheLoadble){
        //查询缓存
        Object redisObj = valueOperations.get(String.valueOf(key));
        if(redisObj!=null){
            return new CommonResult().setCode(200).setData(redisObj).setMsg("OK");
        }
        synchronized (this){
            redisObj = valueOperations.get(String.valueOf(key));
            if(redisObj!=null){
                return new CommonResult().setCode(200).setData(redisObj).setMsg("OK");
            }
            T load = cacheLoadble.load();
            valueOperations.set(String.valueOf(key), load,expire, unit);  //加入缓存
            return new CommonResult().setCode(200).setData(load).setMsg("OK");
        }
    }


    public CommonResult redisFindCache(String key, long expire, TimeUnit unit, CacheLoadble<T> cacheLoadble, boolean b){
        //判断是否走过滤器
        if(b){
            //先走过滤器
            boolean bloomExist = bloomFilter.isExist(String.valueOf(key));
            if(!bloomExist){
                return new CommonResult().setCode(600).setData(null).setMsg("查询无果");
            }
        }
        //查询缓存
        Object redisObj = valueOperations.get(String.valueOf(key));
        //命中缓存
        if(redisObj != null) {
            //判断缓存的是不是空对象
            if(redisObj instanceof NullValueResultDO) {
                //直接返回空对象
                return new CommonResult().setCode(500).setData(redisObj).setMsg("查询无果");
            }
            //正常返回数据
            return new CommonResult().setCode(200).setData(redisObj).setMsg("OK");
        }
        //缓存不命中
//      if(redisLock.tryLock(key)){
        try {
        redisLock.lock(key);
        //查询缓存
        redisObj = valueOperations.get(String.valueOf(key));
        //命中缓存
        if(redisObj != null) {
            //判断缓存的是不是空对象
            if(redisObj instanceof NullValueResultDO) {
                //直接返回空对象
                return new CommonResult().setCode(500).setData(redisObj).setMsg("查询无果");
            }
            //正常返回数据
            return new CommonResult().setCode(200).setData(redisObj).setMsg("OK");
        }

                T load = cacheLoadble.load();//查询数据库
                if (load != null) {
                    valueOperations.set(key, load,expire, unit);  //加入缓存
                    return new CommonResult().setCode(200).setData(load).setMsg("OK");
                }
                //数据库都没有数据  缓存空对象
                valueOperations.set(key, new NullValueResultDO(),60, TimeUnit.SECONDS);
                return new CommonResult().setCode(500).setData(new NullValueResultDO()).setMsg("查询无果");
            }finally {
                redisLock.unlock(key);
            }
//        }
//        else {
//            return redisFindCache(key,expire,unit,cacheLoadble,false);
//        }
    }

}
