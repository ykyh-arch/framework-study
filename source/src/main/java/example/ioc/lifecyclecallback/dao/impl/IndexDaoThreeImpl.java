package example.ioc.lifecyclecallback.dao.impl;

import example.ioc.lifecyclecallback.dao.IndexDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName: IndexDaoImpl
 * @Description: 实现类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:18
 * @Version: 1.0
 **/
@Repository
public class IndexDaoThreeImpl implements IndexDao {

    public IndexDaoThreeImpl() {
        System.out.println("IndexDaoThreeImpl constructor is running");
    }

    public void method() {
        System.out.println("method is running");
    }

    @PostConstruct
    //在构造器执行之后
    public void init()  {
        System.out.println("IndexDaoThreeImpl init is running");
    }

    @PreDestroy
    //在类销毁执行之前
    public void destroy() {
        System.out.println("IndexDaoThreeImpl destory is running");
    }
}
