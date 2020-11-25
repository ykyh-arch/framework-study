package example.proxy.statics;

import example.proxy.dao.impl.IndexDaoImpl;

/**
 * @ClassName: IndexDaoLogImpl
 * @Description: 时间类。
 * @Author: Uetec
 * @Date: 2020-11-24-17:25
 * @Version: 1.0
 **/
public class IndexDaoTimeImpl extends IndexDaoImpl {
    @Override
    public void query() {
        System.out.println("Time data");
        super.query();
    }
}
