package example.aop.xml.main;

import example.aop.xml.dao.IndexDao;
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

        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext-aop.xml");
        IndexDao indexDao = (IndexDao) classPathXmlApplicationContext.getBean("indexDaoImpl");
        indexDao.method1("method1");

    }

}
