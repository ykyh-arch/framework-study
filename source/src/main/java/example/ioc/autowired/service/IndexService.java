package example.ioc.autowired.service;

import example.ioc.autowired.dao.IndexDao;
import example.ioc.autowired.dao.impl.IndexDaoAnotherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: IndexService
 * @Description: 服务层。
 * @Author: Uetec
 * @Date: 2020-11-19-10:19
 * @Version: 1.0
 **/
//指定bean的命名规则，默认使用类名驼峰规则，首字母小写
@Service("service")
//bean的作用域。默认singleton
//@Scope("prototype")
public class IndexService {

    //此类依赖于IndexDao
    //private IndexDao indexDao;

    /*public void service(){
        indexDao.method();
    }*/

    //setter方法注入，byName的依据
    /*public void setIndexDao(IndexDao indexDao) {
        this.indexDao = indexDao;
    }*/

    //@Autowired是默认根据byType进行自动装配，找不到根据Name进行自动装配
    //@Resource是根据属性Name进行自动装配，根据属性名 indexDaoImpl
    //@Resource(name="indexDaoImpl") 或 @Resource(type= IndexDaoAnotherImpl.class) 指定装配文件
    @Autowired
    private IndexDao indexDaoImpl;

    public void service(){
        indexDaoImpl.method();
    }

}
