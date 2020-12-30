package com.example.cache.util;

/**
 * @ClassName: ThreadUtil
 * @Description: 线程工具类。守护线程。
 * @Author: Uetec
 * @Date: 2020-12-30-10:56
 * @Version: 1.0
 **/
public class ThreadUtil {

    public static Thread thread;

    public static Thread newThread(Runnable runnable){
        if(thread ==null){
            synchronized (ThreadUtil.class){
                if(thread ==null){
                    thread = new Thread(runnable);
                    thread.setDaemon(true);
                }
                return thread;
            }
        }
        return thread;
    }
}
