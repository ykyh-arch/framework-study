package example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: FixedThreadPoolUse
 * @Description: 固定线程池。
 * @Author: Uetec
 * @Date: 2020-11-17-17:40
 * @Version: 1.0
 **/
public class FixedThreadPoolUse {

    public static void main(String[] args) {
        ExecutorService pool= Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            Runnable work=new WorkThread();
            pool.execute(work);
        }
        pool.shutdown();
        while (!pool.isTerminated()){//自旋
        }
        System.out.println("finished");
    }

}
//任务
class WorkThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is started.");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
