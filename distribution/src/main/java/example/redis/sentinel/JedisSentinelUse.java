package example.redis.sentinel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName: JedisSentinelUse
 * @Description: JedisSentinel使用测试。哨兵
 * @Author: Uetec
 * @Date: 2020-12-25-11:00
 * @Version: 1.0
 **/
public class JedisSentinelUse {

    public static void main(String[] args)  {

        Logger logger= LoggerFactory.getLogger(JedisSentinelUse.class);
        Set<String> set=new HashSet<String>();
        set.add("192.168.1.49:28000");
        set.add("192.168.1.49:28001");
        set.add("192.168.1.49:28002");
        //哨兵池
        JedisSentinelPool jedisSentinelPool=new JedisSentinelPool("mymaster",set,"123456");
        while (true) {
            Jedis jedis=null;
            try {
                jedis = jedisSentinelPool.getResource();
                String s = UUID.randomUUID().toString();
                jedis.set("k" + s, "v" + s);
                System.out.println(jedis.get("k" + s));
                Thread.sleep(1000);
            }catch (Exception e){
                logger.error(e.getMessage());
            }finally {
                if(jedis!=null){
                    jedis.close();
                }
            }
        }
    }
}
