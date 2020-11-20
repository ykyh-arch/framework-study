package example.ioc.autowired.service;

import example.ioc.autowired.dao.IndexDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IndexService
 * @Description: 服务层。
 * @Author: Uetec
 * @Date: 2020-11-19-10:19
 * @Version: 1.0
 **/
//指定bean的命名规则，默认使用类名驼峰规则，首字母小写
@Service
//bean的作用域。默认singleton
//@Scope("prototype")
public class IndexAnotherService implements ApplicationContextAware {

    @Autowired
    private IndexDao indexDaoImpl;

    private ApplicationContext applicationContext;

    public void service(){
        indexDaoImpl.method();
        //Singleton Beans with Prototype-bean Dependencies：会引发问题，使得得到的Prototype-bean是同一个对象，
        //原因：Singleton Beans 在初始化的时候只初始化一次，而作为其Prototype-bean属性也只实例化一次。
        //解决办法：
        //一、Singleton Beans实现ApplicationContextAware重写setApplicationContext（）方法，缺点：对Spring依赖太高
        //二、利用method-injection
        System.out.println("IndexService: " + this.hashCode());
        System.out.println("IndexDao: " + indexDaoImpl.hashCode());

        System.out.println("IndexDao by applicationContext: " + applicationContext.getBean("indexDaoImpl").hashCode());
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
