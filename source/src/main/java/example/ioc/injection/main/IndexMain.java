package example.ioc.injection.main;

import example.ioc.injection.conf.ApplicationConfig;
import example.ioc.injection.service.IndexService;
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
        /*ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext-xml.xml");*/

        /*ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext-annotation.xml");
        IndexService indexService= (IndexService)classPathXmlApplicationContext.getBean("service");
        indexService.service();*/

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IndexService indexService= (IndexService)annotationConfigApplicationContext.getBean("service");
        indexService.service();

    }

}
