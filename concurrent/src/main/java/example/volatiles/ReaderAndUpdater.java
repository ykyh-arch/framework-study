package example.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ReaderAndUpdater
 * @Description: 读写共享数据，用一个线程读数据，一个线程改数据，存在数据的不一致性？？？
 * @Author: Uetec
 * @Date: 2020-11-10-16:41
 * @Version: 1.0
 **/
public class ReaderAndUpdater {
    final static int MAX=50;
    //volatile 保证线程Reader能感知到线程Updater对init_value操作，可见性
    static volatile int init_value=0;

    public static void main(String[] args) {
        new Thread(()->{
            int localValue=init_value;
            while(localValue<MAX){
                if(localValue!=init_value){
                    System.out.println("Reader:"+init_value);
                    localValue=init_value;
                }
            }
        },"Reader").start();//读数据
        new Thread(()->{
            int localValue=init_value;
            while(localValue<MAX){
                System.out.println("updater:"+(++localValue));
                init_value=localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Updater").start();//改
    }

}
