package example.aop.dao.impl;

import example.aop.dao.IndexDao;
import example.aop.inno.AspectUse;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: IndexDaoImpl
 * @Description: 实现类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:18
 * @Version: 1.0
 **/
@Repository("indexDaoImpl")
public class IndexDaoImpl implements IndexDao {

    public void method1(String str) {
        System.out.println("method1 is running");
    }

    @AspectUse
    public void method2() {
        System.out.println("method2 is running");
    }

}
