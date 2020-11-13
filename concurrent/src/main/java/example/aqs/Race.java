package example.aqs;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Race
 * @Description: 运动员赛跑。CyclicBarrier，回路屏障
 * @Author: Uetec
 * @Date: 2020-11-13-14:46
 * @Version: 1.0
 **/
public class Race {

    public static void main(String[] args) {
        //parties=8，设定的数量
        CyclicBarrier barrier=new CyclicBarrier(8);
        Thread[] player=new Thread[8];
        for (int i = 0; i < 8; i++) {
            player[i]=new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println(Thread.currentThread().getName()+"准备好了");
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //等选手都到齐往下执行
                System.out.println("选手"+Thread.currentThread().getName()+"起跑");
            },"player["+i+"]");
            player[i].start();
        }
    }

}
