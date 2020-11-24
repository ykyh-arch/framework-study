package example.proxy.main;

import example.proxy.dao.impl.IndexDaoImpl;
import example.proxy.proxy.statics.IndexDaoLogImpl;

/**
 * @ClassName: IndexMain
 * @Description: 入口类。
 * @Author: Uetec
 * @Date: 2020-11-24-17:23
 * @Version: 1.0
 **/
public class IndexMain {
    public static void main(String[] args) {
       /*
        * IndexDaoImpl添加日志、时间、权限验证等
        * 基于继承 IndexDaoLogImpl、IndexDaoTimeImpl，IndexDaoImpl目标对象，IndexDaoLogImpl代理对象
        * 基于聚合
        *
        *
        *
        * */
//        IndexDaoImpl indexDaoImpl = new IndexDaoImpl();
        IndexDaoImpl indexDaoImpl = new IndexDaoLogImpl();
        indexDaoImpl.query();

    }
}
