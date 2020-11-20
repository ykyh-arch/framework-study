package example.ioc.dependencies.dao.impl;

import example.ioc.dependencies.dao.IndexDao;
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
@Repository
//bean初始化回调方法，销毁回调方法
//@Primary
public class IndexDaoImpl implements IndexDao, InitializingBean, DisposableBean {

    public IndexDaoImpl() {
        System.out.println("IndexDaoImpl constructor is running");
    }

    public void method() {
        System.out.println("method is running");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("IndexDaoImpl init is running");
    }

    public void destroy() throws Exception {
        System.out.println("IndexDaoImpl destory is running");
    }
}
