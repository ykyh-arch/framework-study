package example.factorybean.main;

import example.factorybean.beans.SelfFactoryBean;
import example.factorybean.beans.TestFactoryBean;
import example.factorybean.conf.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: IndexMain
 * @Description: 入口类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:33
 * @Version: 1.0
 **/
public class IndexMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        SelfFactoryBean selfFactoryBean= (SelfFactoryBean)annotationConfigApplicationContext.getBean("&factoryBean");
        selfFactoryBean.test();

        TestFactoryBean testFactoryBean= (TestFactoryBean)annotationConfigApplicationContext.getBean("factoryBean");
        testFactoryBean.test();


    }

}
