package example.factorybean.main;

import example.factorybean.beans.SelfFactoryBean;
import example.factorybean.beans.TestFactoryBean;
import example.factorybean.conf.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: IndexMain
 * @Description: 入口类。
 *               扫描类的方式：1、在ApplicationConfig中配置
 *                          2、单独注册类
 *                          3、扫描
 * @Author: Uetec
 * @Date: 2020-11-19-10:33
 * @Version: 1.0
 **/
public class IndexMain {

    public static void main(String[] args) {

        /*AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        SelfFactoryBean selfFactoryBean= (SelfFactoryBean)annotationConfigApplicationContext.getBean("&factoryBean");
        selfFactoryBean.test();

        TestFactoryBean testFactoryBean= (TestFactoryBean)annotationConfigApplicationContext.getBean("factoryBean");
        testFactoryBean.test();*/


        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext();
        //注册配置文件，扫描
        annotationConfigApplicationContext.register(ApplicationConfig.class);
        //单独注册类
        //annotationConfigApplicationContext.register(TestFactoryBean.class);
        //扫描
        annotationConfigApplicationContext.scan("example.factorybean");
        annotationConfigApplicationContext.refresh();
        TestFactoryBean testFactoryBean= (TestFactoryBean)annotationConfigApplicationContext.getBean("factoryBean");
        testFactoryBean.test();
    }

}
