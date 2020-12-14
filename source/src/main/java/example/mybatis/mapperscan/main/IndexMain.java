package example.mybatis.mapperscan.main;

import example.mybatis.mapperscan.conf.ApplicationConfig;
import example.mybatis.mapperscan.dao.IndexDao;
import example.mybatis.mapperscan.service.IndexService;
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
        annotationConfigApplicationContext.register(ApplicationConfig.class);
        annotationConfigApplicationContext.refresh();
        annotationConfigApplicationContext.getBean(IndexService.class).select("花");
    }

}
