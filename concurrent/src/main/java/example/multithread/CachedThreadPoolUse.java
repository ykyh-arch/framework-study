package example.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: CachedThreadPoolUse
 * @Description: 可变大小的线程池。
 * @Author: Uetec
 * @Date: 2020-11-17-17:35
 * @Version: 1.0
 **/
public class CachedThreadPoolUse {

    public static void main(String[] args) {
        //线程数根据任务数量自动调整
        ExecutorService pool= Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            //创建任务
            Runnable task=new Task();
            //把任务交给pool去执行
            pool.execute(task);
        }

    }
}
