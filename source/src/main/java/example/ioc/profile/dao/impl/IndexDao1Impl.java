package example.ioc.profile.dao.impl;

import example.ioc.profile.dao.IndexDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: IndexDaoImpl
 * @Description: 实现类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:18
 * @Version: 1.0
 **/
@Repository
@Profile("IndexDao1Impl")
public class IndexDao1Impl implements IndexDao {

    public IndexDao1Impl() {
        System.out.println("IndexDaoImpl constructor is running");
    }

    public void method() {
        System.out.println("method is running");
    }

}
