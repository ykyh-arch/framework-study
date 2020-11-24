package example.aop.main;

import example.aop.conf.ApplicationConfig;
import example.aop.dao.IndexDao;
import example.aop.service.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: ExpandMain
 * @Description: 入口类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:33
 * @Version: 1.0
 **/
public class ExpandMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // ExpandDaoImpl之所以可以使用IndexDao的方法
        // 是因为引入了Introductions，使用了IndexDao的默认实现方法
        IndexDao indexDao = (IndexDao) annotationConfigApplicationContext.getBean("expandDaoImpl");
        indexDao.method1("method1");
    }

}
