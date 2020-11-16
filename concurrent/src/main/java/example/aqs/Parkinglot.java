package example.aqs;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Parkinglot
 * @Description: 停车场，信号量控制
 * @Author: Uetec
 * @Date: 2020-11-16-11:08
 * @Version: 1.0
 **/
public class Parkinglot {
    public static void main(String[] args) {
        //创建Semaphore信号量，容量为5，车位只有5个，10辆车
        Semaphore sp=new Semaphore(5);
        Thread[] car=new Thread[10];
        for (int i = 0; i < 10; i++) {
            car[i]=new Thread(()->{
                //请求许可
                try {
                    sp.acquire();
                    System.out.println(Thread.currentThread().getName()+"可以进停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //使用资源
                try {
                    int val= new Random().nextInt(10);
                    TimeUnit.SECONDS.sleep(val);
                    System.out.println(Thread.currentThread().getName()+"停留了"+val+"秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //离开（释放资源）
                try {
                    sp.release();
                    System.out.println(Thread.currentThread().getName()+"离开停车场");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"car["+i+"]");
            car[i].start();
        }

    }
}