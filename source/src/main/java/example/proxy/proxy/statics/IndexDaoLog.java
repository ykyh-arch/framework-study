package example.proxy.proxy.statics;

import example.proxy.dao.IndexDao;

/**
 * @ClassName: IndexDaoLogImpl
 * @Description: 日志类。
 * @Author: Uetec
 * @Date: 2020-11-24-17:25
 * @Version: 1.0
 **/
public class IndexDaoLog implements IndexDao {

    public void query() {
        System.out.println("Log data");

    }
}
