package example.proxy.statics;

import example.proxy.dao.IndexDao;

/**
 * @ClassName: IndexDaoLog
 * @Description: 日志类。
 * @Author: Uetec
 * @Date: 2020-11-24-17:25
 * @Version: 1.0
 **/
public class IndexDaoLog implements IndexDao {

    //聚合
    IndexDao indexDao;

    public IndexDaoLog(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    public void query() {
        System.out.println("Log data");
        indexDao.query();
    }

    @Override
    public String query(String str) {
        return indexDao.query(str);
    }
}
