package example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName: ScheduledThreadPoolUse
 * @Description: 调度线程池。
 * @Author: Uetec
 * @Date: 2020-11-18-9:30
 * @Version: 1.0
 **/
public class ScheduledThreadPoolUse {
    public static void main(String[] args) {
        ScheduledExecutorService pool= Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable task=new Task();
            //把任务交给pool去执行
            pool.execute(task);
        }
    }
}

class SingleThreadExecutorUse{

    public static void main(String[] args) {
        ExecutorService pool=Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            //创建任务
            Runnable task=new Task();
            //把任务交给pool去执行
            pool.execute(task);
        }
    }

}
