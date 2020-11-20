package example.ioc.lifecyclecallback.dao.impl;

import example.ioc.lifecyclecallback.dao.IndexDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: IndexDaoImpl
 * @Description: 实现类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:18
 * @Version: 1.0
 **/
//@Repository
public class IndexDaoAnotherImpl implements IndexDao {

    public IndexDaoAnotherImpl() {
        System.out.println("IndexDaoAnotherImpl constructor is running");
    }

    public void method() {
        System.out.println("another method is running");
    }

    //bean初始化回调方法，销毁回调方法
    private void intMethod() {
        System.out.println("IndexDaoAnotherImpl init is running");
    }

    private void destoryMethod() {
        System.out.println("IndexDaoAnotherImpl destoryMethod is running");
    }
}
