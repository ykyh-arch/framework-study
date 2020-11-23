package example.ioc.profile.main;

import example.ioc.profile.conf.ApplicationConfig;
import example.ioc.profile.dao.IndexDao;
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

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        //指定Profile文件
        annotationConfigApplicationContext.getEnvironment().setActiveProfiles("IndexDao1Impl");
        annotationConfigApplicationContext.register(ApplicationConfig.class);
        annotationConfigApplicationContext.refresh();
        System.out.println("----------------------------------");
        System.out.println(annotationConfigApplicationContext.getBean(IndexDao.class).getClass().getSimpleName());
    }

}
