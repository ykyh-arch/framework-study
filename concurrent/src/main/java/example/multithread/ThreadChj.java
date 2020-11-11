package example.multithread;

/**
 * @ClassName: ThreadChj
 * @Description: 线程创建
 * @Author: Uetec
 * @Date: 2020-11-02-11:23
 * @Version: 1.0
 **/
public class ThreadChj{

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();

        new Thread(new Thread2()).start();
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

