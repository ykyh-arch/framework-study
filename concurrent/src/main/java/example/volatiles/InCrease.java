package example.volatiles;

/**
 * @ClassName: InCrease
 * @Description: 自增，无法保证原子性
 * @Author: Uetec
 * @Date: 2020-11-10-17:19
 * @Version: 1.0
 **/
public class InCrease {

    static volatile int m=0;

    public  static void increase(){
        //非原子操作
        m++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            //10个线程
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    increase();
                }
            }).start();
        }//0，分析为什么是0???
        System.out.println(m);
    }
}
