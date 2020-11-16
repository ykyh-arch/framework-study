package example.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: ThreadChj
 * @Description: 线程创建
 * @Author: Uetec
 * @Date: 2020-11-02-11:23
 * @Version: 1.0
 **/
public class ThreadChj{

    public static void main(String[] args) throws Exception {
        Thread1 thread1 = new Thread1();
        thread1.start();

        new Thread(new Thread2()).start();

        //创建FutureTask的对象
        FutureTask<String> task = new FutureTask<String>(new Thread3());
        //创建Thread类的对象
        Thread thread3 = new Thread(task,"thread-3");
        //开启线程
        thread3.start();
        //获取call()方法的返回值，即线程运行结束后的返回值
        String result = task.get();
        System.out.println(result);

    }
}
//方式一：继承Thread
class Thread1 extends Thread{

    @Override
    public void run() {
        System.out.println("线程名:"+Thread.currentThread().getName());
    }

}
//方式二：：实现Runnable
class Thread2 implements Runnable{

    public void run() {
        System.out.println("线程名:"+Thread.currentThread().getName());
    }
}

//方式三：：实现Callable
class Thread3 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("线程名:"+Thread.currentThread().getName());
        return "线程名:"+Thread.currentThread().getName();
    }
}

