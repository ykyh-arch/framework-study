package example.redis.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @ClassName: PipelineUse
 * @Description: 管道使用测试。
 * @Author: Uetec
 * @Date: 2020-12-28-16:00
 * @Version: 1.0
 **/
public class PipelineUse {

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("192.168.1.49",6379);
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            jedis.hset("k"+i,"f"+i,"v"+i);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println((end - start));//11354
//        jedis.close();

        Jedis jedis = new Jedis("192.168.1.49",6379);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            //分断提交
            Pipeline pipeline =  jedis.pipelined();
            for (int j = i * 100; j < (i+1)* 100; j++) {
                pipeline.hset("p"+j,"f"+j,"v"+j);
            }
            //同步并返回所有
            pipeline.syncAndReturnAll();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start));//377
        jedis.close();

    }
}
