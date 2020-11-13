package example.aqs;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: IncreaseRepeat
 * @Description: 可重入锁，同一个锁对同一资源进行占有的时候，直接分配给这个线程。
 * @Author: Uetec
 * @Date: 2020-11-13-14:22
 * @Version: 1.0
 **/
public class IncreaseRepeat {

//  private YkyhLock lock=new YkyhLock();
    //JUC提供的可重入锁
    private ReentrantLock lock=new ReentrantLock();
    //读写锁，支持可选的公平策略。
    //ReentrantReadWriteLock

    public void method1(){
        lock.lock();
        System.out.println("method1");
        method2();
        lock.unlock();
    }
    public void method2(){
        lock.lock();
        System.out.println("method2");
        lock.unlock();
    }

    public static void main(String[] args) {
        IncreaseRepeat incr=new IncreaseRepeat();
            new Thread(()->{
                incr.method1();
            }).start();

    }

}
