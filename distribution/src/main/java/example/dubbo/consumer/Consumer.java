package example.dubbo.consumer;

import example.dubbo.api.IndexService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Consumer
 * @Description: 启动类。
 * @Author: Uetec
 * @Date: 2020-12-31-9:58
 * @Version: 1.0
 **/
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        classPathXmlApplicationContext.start();
        IndexService indexService = (IndexService) classPathXmlApplicationContext.getBean("indexService");
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println(indexService.index("hello"));
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
