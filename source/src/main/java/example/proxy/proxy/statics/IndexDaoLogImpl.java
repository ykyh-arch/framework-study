package example.proxy.proxy.statics;

import example.proxy.dao.impl.IndexDaoImpl;

/**
 * @ClassName: IndexDaoLogImpl
 * @Description: 日志类。
 * @Author: Uetec
 * @Date: 2020-11-24-17:25
 * @Version: 1.0
 **/
public class IndexDaoLogImpl extends IndexDaoImpl {
    @Override
    public void query() {
        System.out.println("Log data");
        super.query();
    }
}
