package example.collection;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @ClassName: ConcurrentLinkedDequeUse
 * @Description: ConcurrentLinkedDeque使用，并发操作。
 * @Author: Uetec
 * @Date: 2020-11-16-14:14
 * @Version: 1.0
 **/
public class ConcurrentLinkedDequeUse {

    public static void main(String[] args) throws InterruptedException{
        //并发非阻塞队列，链表双向队列FIFO
        ConcurrentLinkedDeque<String> list=new ConcurrentLinkedDeque();
        //添加数据
        Thread[] add=new Thread[100];
        for (int i = 0; i < 100; i++) {
            add[i]=new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    list.add(Thread.currentThread().getName()+":Element "+j);
                }
            });
            add[i].start();
            //Join线程通讯
            add[i].join();
        }
        System.out.println("after add size:"+list.size());

        //移除数据
        Thread[] poll=new Thread[100];
        for (int i = 0; i < 100; i++) {
            poll[i]=new Thread(()->{
                for (int j = 0; j < 5000; j++) {
                    list.pollLast();
                    list.pollFirst();
                }
            });
            poll[i].start();
            poll[i].join();

        }
        System.out.println("after poll size:"+list.size());
    }

}
