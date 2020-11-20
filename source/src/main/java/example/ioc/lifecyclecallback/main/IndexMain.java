package example.ioc.lifecyclecallback.main;

import example.ioc.lifecyclecallback.conf.ApplicationConfig;
import example.ioc.lifecyclecallback.service.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: IndexMain
 * @Description: 入口类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:33
 * @Version: 1.0
 **/
public class IndexMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        System.out.println("----------------------------------");
        //容器中如何剔除这个类？使用包扫描注解，剔除
        System.out.println(annotationConfigApplicationContext.getBean("service").getClass().getSimpleName());
    }

}
