package org.example.main;

import org.example.conf.ApplicationConfig;
import org.example.service.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: Main
 * @Description: 入口类。
 * 一级缓存与二级缓存：
 * 一级缓存是用户级别（线程），当一个用户执行数据操作时，先执行Mysql Db查询，然后就查询结果缓存起来，
 * 当该用户再次执行一样的数据操作时直接从缓存中取；但当与spring整合时，一级缓存会失效，因为一级缓存是基于sqlSession的，而spring在
 * 执行完线程时会关闭session，导致缓存失效，所以mybaits一级缓存实际没什么意义；
 * 二级缓存是共享线程的，基于命名空间的（CacheNamespace），同一命名空间下缓存有效，但在不同的命名空间下，缓存就失去了意义；
 * @Author: Uetec
 * @Date: 2021-01-13-15:11
 * @Version: 1.0
 **/
public class Main {

    public static void main(String[] args) {
        //org.apache.ibatis.logging.LogFactory.useLog4JLogging();
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        applicationContext.start();
        System.out.println(applicationContext.getBean(IndexService.class).select("花"));
    }

}
