package example.aqs;

/**
 * @ClassName: IncreaseUpdate
 * @Description: 利用自定义锁保证数据的一致性问题
 * @Author: Uetec
 * @Date: 2020-11-13-14:14
 * @Version: 1.0
 **/
public class IncreaseUpdate {

    private YkyhLock lock=new YkyhLock();

    private volatile int m=0;

    public int next(){
        try {
            lock.lock();
            return m++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        IncreaseUpdate incr=new IncreaseUpdate();
        Thread[] th=new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i]=new Thread(()->{
                System.out.println(Thread.currentThread().getName()+":"+incr.next());
            });
            th[i].start();
        }
    }
}
