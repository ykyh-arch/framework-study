package example.ioc.autowired.main;

import example.ioc.autowired.service.IndexService;
import example.ioc.autowired.conf.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
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
        /*ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext-autowired-xml.xml");
        IndexService indexService= (IndexService)classPathXmlApplicationContext.getBean("service");
        indexService.service();*/

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IndexService indexService= (IndexService)annotationConfigApplicationContext.getBean("service");
        indexService.service();

        IndexService indexAnathorService= (IndexService)annotationConfigApplicationContext.getBean("service");
        indexService.service();

        //比较是否为同一对象
        System.out.println(indexAnathorService.hashCode() == indexService.hashCode());

    }

}
