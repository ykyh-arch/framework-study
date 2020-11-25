package example.proxy.statics;

import example.proxy.dao.IndexDao;

/**
 * @ClassName: IndexDaoTime
 * @Description: 时间类。
 * @Author: Uetec
 * @Date: 2020-11-24-17:25
 * @Version: 1.0
 **/
public class IndexDaoTime implements IndexDao {

    //聚合
    IndexDao indexDao;

    public IndexDaoTime(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    public void query() {
        System.out.println("Time data");
        indexDao.query();
    }

    @Override
    public String query(String str) {
        return indexDao.query(str);
    }
}
