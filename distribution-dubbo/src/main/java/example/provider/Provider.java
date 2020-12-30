package example.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @ClassName: Provider
 * @Description: 启动类。
 * @Author: Uetec
 * @Date: 2020-12-30-17:37
 * @Version: 1.0
 **/
public class Provider {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("dubbo-provider.xml");
        classPathXmlApplicationContext.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
