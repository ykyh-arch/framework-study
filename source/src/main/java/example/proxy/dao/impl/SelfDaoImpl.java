package example.proxy.dao.impl;

import example.proxy.dao.IndexDao;
import example.proxy.dao.SelfDao;

/**
 * @ClassName: IndexDaoImpl
 * @Description: 实现类。
 * @Author: Uetec
 * @Date: 2020-11-24-17:03
 * @Version: 1.0
 **/
public class SelfDaoImpl implements SelfDao {

    public String query()  throws Exception{
        System.out.println("query data");
        return null;
    }

}
