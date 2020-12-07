package example.distributed.config;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Main
 * @Description: 测试类。
 * @Author: Uetec
 * @Date: 2020-12-07-11:15
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        ZkConfig config =new ZkConfig();
        config.save("timeout","1000");
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println(config.get("timeout"));
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
