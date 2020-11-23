package example.ioc.dependencies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IndexAnotherService
 * @Description: 服务层。
 * @Author: Uetec
 * @Date: 2020-11-19-10:19
 * @Version: 1.0
 **/
@Service
@Scope("prototype")
public class IndexAnotherService {

    //circular reference?循环引用、，默认单例可以，原型不行
    //可以理解：单例将对象放在缓存里，引用从缓存取、而原型每次获取getBean是新new的对象，可能对象没创建成功引用不到
    @Autowired
    private IndexService indexService;

}
