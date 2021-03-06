package example.ioc.dependencies.service;

import example.ioc.dependencies.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IndexService
 * @Description: 服务层。
 * @Author: Uetec
 * @Date: 2020-11-19-10:19
 * @Version: 1.0
 **/
@Service("service")
//@Scope("prototype")
public class IndexService {

    /*
     * @Autowired默认根据ByTpe实现类的自动装配，找不到
     * 再根据Name属性自动装配
     * 由于找不到会报错，解决方案：指定主实现类@Primary
     * 或 使用@Qualifier限定符来指定
     */
    @Autowired
    @Qualifier("indexDaoImpl")
    private IndexDao indexDao;

    @Autowired
    private IndexAnotherService indexAnotherService;


}
