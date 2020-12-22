package example.redis.jedis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: JedisUse
 * @Description: redis客户端使用。
 * @Author: Uetec
 * @Date: 2020-12-22-10:28
 * @Version: 1.0
 **/
public class JedisUse {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.49",6379);
        System.out.println(jedis.ping());
        jedis.close();
    }

    private Jedis jedis;

    @Before
    public void init(){
        jedis = new Jedis("192.168.1.49",6379);
    }

    @Test
    public void testKey(){
        System.out.println(jedis.set("k1","v1"));
        //生成环境不建议使用keys *
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.type("k1"));
        System.out.println(jedis.expire("k1", 60));
        System.out.println(jedis.ttl("k1"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.decr("count"));
        System.out.println(jedis.decr("count"));
        System.out.println(jedis.incrBy("count",2));
        System.out.println(jedis.decrBy("count",2));

    }

    @Test
    public void testString(){
        System.out.println(jedis.set("k1","v11"));
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.del("k1"));
        //追加
        System.out.println(jedis.append("k1", "v1"));
        System.out.println(jedis.append("k1", "v1"));
        System.out.println(jedis.strlen("k1"));

        //一次请求，批量设置
        System.out.println(jedis.mset("k1", "v1","k2", "v2","k3", "v3"));
        System.out.println(jedis.mget("k1","k2","k3"));

        //不存在则设值
        System.out.println(jedis.setnx("k1","v1"));
        //设置过期时间
        System.out.println(jedis.setex("k1",60,"v1"));

        //分布式锁方案
        SetParams params = new SetParams();
        params.ex(100);
        params.nx();
        System.out.println(jedis.set("k1", "v1", params));

    }

    @Test
    public void testList(){
        System.out.println(jedis.lpush("lpush","v1","v2","v3","v4"));
        System.out.println(jedis.rpush("rpush","v1","v2","v3","v4"));
        System.out.println(jedis.lrange("lpush",0,-1));
        System.out.println(jedis.lrange("rpush",0,-1));
        System.out.println(jedis.lpop("lpush"));
        System.out.println(jedis.rpop("rpush"));
    }

    @Test
    public void testSet(){
        System.out.println(jedis.sadd("set1","v1","v2","v3","v4"));
        System.out.println(jedis.sadd("set2","v3","v4","v5"));
        System.out.println(jedis.sismember("set1","v1"));
        //无序
        System.out.println(jedis.smembers("set1"));
        //差集
        System.out.println(jedis.sdiff("set1","set2"));
        //交集
        System.out.println(jedis.sinter("set1","set2"));
        //并集
        System.out.println(jedis.sunion("set1","set2"));
    }

    @Test
    public void testHash(){
        Map<String,String> params = new HashMap<String, String>();
        params.put("K1","V1");
        params.put("K2","V2");
        params.put("K3","V3");
        System.out.println(jedis.hset("hash",params));
        System.out.println(jedis.hkeys("hash"));
        System.out.println(jedis.hvals("hash"));
        System.out.println(jedis.hgetAll("hash"));
    }

    @Test
    public void testZset(){
        Map<String,Double> params = new HashMap<String, Double>();
        params.put("K1",10d);
        params.put("K2",11d);
        params.put("K3",12d);
        System.out.println(jedis.zadd("zset1",params));
        System.out.println(jedis.zrange("zset1",0,-1));
        System.out.println(jedis.zrangeWithScores("zset1",0,-1));
    }

    @Test
    public void testScan(){
        this.methodScan("0");
    }

    /**
     * 类似于分页效果
     * @param cursor
     */
    public void methodScan(String cursor){
        String key = "k*";
        ScanParams params =new ScanParams();
        params.match(key);
        params.count(3);
        //指令：scan 0 match k*  count  3
        ScanResult<String> scanResult = jedis.scan(cursor,params);
        cursor = scanResult.getCursor();
        System.out.println("返回的游标为："+cursor);
        List<String> list = scanResult.getResult();
        System.out.println("-------------------");
        for (int i = 0; i < list.size(); i++) {
            String keyResult = list.get(i);
            System.out.println("返回的key:"+keyResult);
        }
        if(!"0".equals(cursor)){
            this.methodScan(cursor);
        }
    }
}
