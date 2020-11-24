package example.aop.service;

import example.aop.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IndexService
 * @Description: 服务层。
 * @Author: Uetec
 * @Date: 2020-11-19-10:19
 * @Version: 1.0
 **/
@Service
public class IndexService {

    @Autowired
    private IndexDao indexDao;

    public void service(String str){
        indexDao.method1(str);
        //indexDao.method2();
    }

}
