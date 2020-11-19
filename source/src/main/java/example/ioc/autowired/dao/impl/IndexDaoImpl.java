package example.ioc.autowired.dao.impl;

import example.ioc.autowired.dao.IndexDao;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: IndexDaoImpl
 * @Description: 实现类。
 * @Author: Uetec
 * @Date: 2020-11-19-10:18
 * @Version: 1.0
 **/
@Repository
public class IndexDaoImpl implements IndexDao {

    public void method() {
        System.out.println("method is running");
    }

}
