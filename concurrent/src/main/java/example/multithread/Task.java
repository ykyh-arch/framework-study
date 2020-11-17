package example.multithread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Task
 * @Description: 任务，简单任务
 * @Author: Uetec
 * @Date: 2020-11-17-17:33
 * @Version: 1.0
 **/
public class Task implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running");
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
