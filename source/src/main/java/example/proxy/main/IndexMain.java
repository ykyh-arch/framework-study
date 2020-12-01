package example.proxy.main;

import example.proxy.dao.IndexDao;
import example.proxy.dao.SelfDao;
import example.proxy.dao.impl.IndexDaoImpl;
import example.proxy.dao.impl.SelfDaoImpl;
import example.proxy.dynamic.ProxyUtil;
import example.proxy.handler.AnotherSelfInvocationHanler;
import example.proxy.handler.SelfInvocationHandler;
import example.proxy.statics.IndexDaoLog;
import example.proxy.statics.IndexDaoLogImpl;
import example.proxy.statics.IndexDaoTime;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

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
        * 缺点：产生类爆炸
        *
        * */
//        IndexDaoImpl indexDaoImpl = new IndexDaoImpl();
        IndexDaoImpl indexDaoImpl = new IndexDaoLogImpl();
        //IndexDaoImpl indexDaoImpl = new IndexDaoTimeImpl();
        indexDaoImpl.query();

        System.out.println("---------------------");

//        IndexDao target = new IndexDaoImpl();
//        IndexDao proxy = new IndexDaoLog(target);
//        proxy.query();

//        System.out.println("---------------------");
        // log + logic
        IndexDao target = new IndexDaoLog(new IndexDaoImpl());
        // time + log + logic
        IndexDao proxy = new IndexDaoTime(target);
        proxy.query();

        System.out.println("---------dynamic proxy------------");

//        IndexDao proxyIndexDao = (IndexDao)ProxyUtil.newInstance(new IndexDaoImpl());
//        proxyIndexDao.query();

//        System.out.println(proxyIndexDao.query("hello word"));
        //JDK动态代理
        IndexDao jdkProxy = (IndexDao) Proxy.newProxyInstance(IndexMain.class.getClassLoader(),
                new Class<?>[] {
                        IndexDao.class
                },new SelfInvocationHandler(new IndexDaoImpl()));
        jdkProxy.query();
        //模拟JDK动态代理

        SelfDao imitatejdkProxy = (SelfDao) ProxyUtil.newProxyInstance(SelfDao.class,new AnotherSelfInvocationHanler(new SelfDaoImpl()));

        try {
            imitatejdkProxy.query();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //JDK代理核心实现
        byte[] bytes= ProxyGenerator.generateProxyClass("$Proxy1",new Class[]{SelfDao.class});
        try {
            //字节流
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Uetec\\Desktop\\$Proxy1.class");
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
