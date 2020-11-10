package example.synchronize;

/**
 * @ClassName: Ticket
 * @Description: 售票问题，参考：SYNCHRONIZED.docx文档
 * @Author: Uetec
 * @Date: 2020-11-10-11:14
 * @Version: 1.0
 **/
public class Ticket extends Thread{

    private static int index=1;
    private static final int MAX=5000;

    @Override
    public void run() {
        //加锁，排他性
        synchronized (this){
            while(index<=MAX){
                System.out.println(Thread.currentThread().getName()+"叫到号码是："+(index++));
            }
        }
    }

    public static void main(String[] args) {
        //5个线程，叫号
        Ticket t1=new Ticket();
        Ticket t2=new Ticket();
        Ticket t3=new Ticket();
        Ticket t4=new Ticket();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
