package example.ioc.injection.service;

import example.ioc.injection.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IndexService
 * @Description: 服务层。
 * @Author: Uetec
 * @Date: 2020-11-19-10:19
 * @Version: 1.0
 **/
@Service("service")
public class IndexService {

    @Autowired
    private IndexDao indexDao;

    public void service(){
        indexDao.method();
    }

    //setter方法注入
//    public void setIndexDao(IndexDao indexDao) {
//        this.indexDao = indexDao;
//    }
    //构造方法注入
    public IndexService(IndexDao indexDao) {
        this.indexDao = indexDao;
    }
}
