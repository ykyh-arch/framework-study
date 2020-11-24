package example.aop.main;

import example.aop.conf.ApplicationConfig;
import example.aop.dao.IndexDao;
import example.aop.dao.impl.IndexDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: ExpandMain
 * @Description: 入口类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:33
 * @Version: 1.0
 **/
public class ModelMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IndexDao indexDao1 = (IndexDao) annotationConfigApplicationContext.getBean("indexDaoImpl");
        IndexDao indexDao2 = (IndexDao) annotationConfigApplicationContext.getBean("indexDaoImpl");
        //System.out.println("IndexDaoImpl hashCode: " + this.hashCode());
        System.out.println(indexDao1.hashCode());
        System.out.println(indexDao2.hashCode());
        indexDao1.method1("method1");
        indexDao1.method1("another method1");
    }

}
