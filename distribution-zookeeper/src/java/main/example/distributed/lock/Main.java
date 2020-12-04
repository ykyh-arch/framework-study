package example.distributed.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Main
 * @Description: 入口类。下订单->减库存->去支付
 * @Author: Uetec
 * @Date: 2020-12-04-15:30
 * @Version: 1.0
 **/
public class Main {
    public static void main(String[] args) {
        Thread th1 =new Thread(new userThread(),"thread1");
        Thread th2 =new Thread(new userThread(),"thread2");
        th1.start();
        th2.start();
    }
    //可重入锁
//    static Lock lock = new ReentrantLock();
    static Lock lock = new ZKlock();
    static class userThread implements Runnable{

        @Override
        public void run() {
            new Order().createOrder();
            lock.lock();
            boolean result = new Stock().reduceStock();
            lock.unlock();
            if(result){
                System.out.println(Thread.currentThread().getName()+"减库存成功");
                new Pay().pay();
            }else{
                System.out.println(Thread.currentThread().getName()+"减库存失败");
            }
        }
    }

}
