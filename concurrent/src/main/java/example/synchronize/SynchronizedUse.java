package example.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SynchronizedUse
 * @Description: 使用说明
 * @Author: Uetec
 * @Date: 2020-11-10-11:19
 * @Version: 1.0
 **/
public class SynchronizedUse {
    //修饰静态方法
    public synchronized static void accessResources0(){
        try {
            //延迟2秒
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+" is runing");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //修饰非静态方法
    public synchronized void accessResources1(){
        try {
            //延迟2秒
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+" is runing");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //代码块（对象）this指的是当前对象
//    public  void accessResources1(){
//        //重入锁
//        synchronized(this){
//            try {
//                synchronized (this){
//                    TimeUnit.MINUTES.sleep(2);
//                }
//                System.out.println(Thread.currentThread().getName()+" is runing");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    //代码块（CLASS类）
    public  void accessResources4(){
        synchronized(SynchronizedUse.class){//ClassLoader加载class类——-》堆 Class对象
            //有Class对象的所有的对象都共同使用这一个锁
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+" is runing");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final SynchronizedUse demo=new SynchronizedUse();
        for (int i = 0; i < 5; i++) {
            new Thread(demo::accessResources1).start();
        }
    }
}
