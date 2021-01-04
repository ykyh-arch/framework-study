package example.dubbo.api;

import example.dubbo.api.IndexService;

/**
 * @ClassName: IndexServiceStub
 * @Description: 本地存根。
 * @Author: Uetec
 * @Date: 2021-01-04-16:15
 * @Version: 1.0
 **/
public class IndexServiceStub implements IndexService {

    private final IndexService indexService;

    // 构造函数传入真正的远程代理对象
    public IndexServiceStub(IndexService indexService){
        this.indexService = indexService;
    }

    @Override
    public String index(String arg) {
        // 此代码在客户端执行, 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
        try {
            return indexService.index(arg);
        } catch (Exception e) {
            // 你可以容错，可以做任何AOP拦截事项
            return "容错数据";
        }
    }

}
