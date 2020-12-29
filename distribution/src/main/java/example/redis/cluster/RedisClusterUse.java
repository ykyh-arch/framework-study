package example.redis.cluster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName: RedisClusterUse
 * @Description: RedisClusterUse
 * @Author: Uetec
 * @Date: 2020-12-28-14:54
 * @Version: 1.0
 **/
public class RedisClusterUse {

    public static void main(String[] args) {

        Logger logger= LoggerFactory.getLogger(RedisClusterUse.class);
        Set<HostAndPort> nodesList=new HashSet<HostAndPort>();
        nodesList.add(new HostAndPort("192.168.1.49",7000));
        nodesList.add(new HostAndPort("192.168.1.49",7001));
        nodesList.add(new HostAndPort("192.168.1.49",7002));
        nodesList.add(new HostAndPort("192.168.1.49",7003));
        nodesList.add(new HostAndPort("192.168.1.49",7004));
        nodesList.add(new HostAndPort("192.168.1.49",7005));

        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(200);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(1000);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(100);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(3000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(false);
        JedisCluster jedisCluster=new JedisCluster(nodesList,jedisPoolConfig);
        //不支持批处理
        //No way to dispatch this command to Redis Cluster because keys have different slots.
//        System.out.println(jedisCluster.mset("k1", "v1", "k2", "v2", "k3", "v3"));
//        System.out.println(jedisCluster.mget("k1","k2", "k3" ));
        while (true) {
            try {
                String s = UUID.randomUUID().toString();
                jedisCluster.set("k" + s, "v" + s);
                System.out.println(jedisCluster.get("k" + s));
                Thread.sleep(1000);
            }catch (Exception e){
                logger.error(e.getMessage());
            }finally {
                if(jedisCluster!=null){
                    jedisCluster.close();
                }
            }
        }
    }


}
