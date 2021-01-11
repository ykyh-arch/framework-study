package org.example.main;

import org.example.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: Main
 * @Description: 入口类。
 * 默认：spring 4使用的是log4j的日志
 * @Author: Uetec
 * @Date: 2021-01-11-11:47
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        applicationContext.start();
    }
}
