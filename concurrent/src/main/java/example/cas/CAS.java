package example.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CAS
 * @Description: CAS算法
 * @Author: Uetec
 * @Date: 2020-11-12-13:46
 * @Version: 1.0
 **/
public class CAS {
    private static volatile int m=0;

    private static AtomicInteger atomicI=new AtomicInteger(0);

    public  static void increase1(){
        //非原子操作，JVM指定命令
        m++;
    }

    public  static void increase2(){
        //原子操作，硬件CPU保证原子性
        atomicI.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] tf = new Thread[20];
        for (int i = 0; i < 20; i++) {
            //20个线程
            tf[i] = new Thread(()->{
                increase1();
            });
            tf[i].start();
            //线程加入，group
            tf[i].join();
        }//0，分析为什么是0???
        System.out.println(m);

        Thread[] td = new Thread[20];
        for (int i = 0; i < 20; i++) {
            //20个线程
            td[i] = new Thread(()->{
                increase2();
            });
            td[i].start();
            //线程加入，group，线程有了交互性
            td[i].join();
        }
        System.out.println(atomicI.get());
    }

}
