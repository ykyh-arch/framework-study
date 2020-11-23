package example.ioc.profile.dao.impl;

import example.ioc.profile.dao.IndexDao;
import org.springframework.context.annotation.Profile;
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
@Profile("IndexDao2Impl")
public class IndexDao2Impl implements IndexDao {

    public IndexDao2Impl() {
        System.out.println("IndexDaoThreeImpl constructor is running");
    }

    public void method() {
        System.out.println("method is running");
    }

}
