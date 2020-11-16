package example.collection;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LinkedBlockingDequeUse
 * @Description: ConcurrentLinkedDeque使用，并发操作。
 * @Author: Uetec
 * @Date: 2020-11-16-14:30
 * @Version: 1.0
 **/
public class LinkedBlockingDequeUse {
    public static void main(String[] args) {
        //并发阻塞的队列
        LinkedBlockingDeque<String> list=new LinkedBlockingDeque(3);
        Thread thread=new Thread(()->{
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    String str=new String(i+":"+j);
                    try {
                        list.put(str.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("client:"+str+" time："+(new Date()));
                }
            }
        });
        thread.start();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    String str=list.take();
                    System.out.println("main:take "+str+" size:"+list.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("end");

    }
}
