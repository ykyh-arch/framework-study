package example.aop.main;

import example.aop.conf.ApplicationConfig;
import example.aop.dao.IndexDao;
import example.aop.dao.impl.IndexDaoImpl;
import example.aop.service.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

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
        IndexService indexService = (IndexService) annotationConfigApplicationContext.getBean(IndexService.class);
        indexService.service();
        //报错：No qualifying bean of type 'example.aop.dao.impl.IndexDaoImpl' available
//        IndexDaoImpl indexDao = (IndexDaoImpl) annotationConfigApplicationContext.getBean(IndexDaoImpl.class);
        IndexDao indexDao = (IndexDao) annotationConfigApplicationContext.getBean("indexDaoImpl");
        //true时才用的是CGLIB代理，proxyTargetClass = true
        //false时才用的是JDK代理，proxyTargetClass = false，底层基于接口，是extends Proxy对象
        System.out.println(indexDao instanceof IndexDaoImpl);
//        System.out.println(indexDao instanceof Proxy);

        /*Class<?>[] interfaces =  new Class<?>[]{IndexDao.class};
        byte[] bytes= ProxyGenerator.generateProxyClass("IndexDaoImpl",interfaces);

        File file = new File("C:\\Users\\Uetec\\Desktop\\IndexDaoImpl.class");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
            //结果：桌面生成IndexDaoImpl.class文件
            //细节：public final class IndexDaoImpl extends Proxy implements IndexDao {...
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

}
