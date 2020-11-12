package example.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @ClassName: ABA
 * @Description: ABA问题
 * @Author: Uetec
 * @Date: 2020-11-12-14:24
 * @Version: 1.0
 **/
public class ABA {

    private static AtomicInteger atomicI=new AtomicInteger(100);
    private static AtomicStampedReference asr=new AtomicStampedReference(100,1);

    public static void main(String[] args) throws InterruptedException{

        Thread t1 = new Thread(()->{
            atomicI.compareAndSet(100,110);
            System.out.println(atomicI.get());
            });
//        t1.start();

        Thread t2 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicI.compareAndSet(110,100);
            System.out.println(atomicI.get());
        });
//        t2.start();

        Thread t3 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicI.compareAndSet(100,120);
            System.out.println(atomicI.get());
        });
//        t3.start();

        //100->110->100 对于线程3来说，无法感知到中间110的变化，还是之前的100
        System.out.println("------------------------------------");
        Thread tf1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asr.compareAndSet(100,110,asr.getStamp(), asr.getStamp()+1);
            System.out.println(asr.getReference());
            asr.compareAndSet(110,100,asr.getStamp(), asr.getStamp()+1);//3
            System.out.println(asr.getReference());

        });

        Thread tf2 = new Thread(()->{
            int stamp = asr.getStamp();//1
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asr.compareAndSet(100,120,stamp, stamp+1);//操作失败
            System.out.println(asr.getReference());

        });

        tf1.start();
        tf2.start();

    }
}
