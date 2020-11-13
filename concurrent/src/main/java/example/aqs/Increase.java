package example.aqs;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Increase
 * @Description: 加加操作，数据不一致问题（存在跳号、重号等问题），解决方案：加锁Lock
 * @Author: Uetec
 * @Date: 2020-11-13-11:13
 * @Version: 1.0
 **/
public class Increase {

    private volatile int m=0;
    public int next(){
        try {
            TimeUnit.SECONDS.sleep(2);
            return m++;
        } catch (InterruptedException e) {
            throw new RuntimeException("ERROR");
        }
    }

    public static void main(String[] args) {
        Increase incr=new Increase();
        Thread[] th=new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i]=new Thread(()->{
                System.out.println(Thread.currentThread().getName()+":"+incr.next());
            });
            th[i].start();
        }
    }

}
