package example.dcl;

/**
 * @ClassName: HungeryMode
 * @Description: 饿汉式
 * 线程安全性：JVM ClassLoader在加载的时候已经被实例化，所以只有这一次，线程安全的。
 * 懒加载：没有延迟加载，好长时间不使用，影响性能。
 * 性能：比较好。
 * @Author: Uetec
 * @Date: 2020-11-11-14:19
 * @Version: 1.0
 **/
public class HungeryMode {
    //加载的时候就产生的实例对象,ClassLoader
    private byte[] data=new byte[1024];
    private static HungeryMode instance=new HungeryMode();
    private HungeryMode(){
    }

    //返回实例对象
    public static HungeryMode getInstance(){
        return instance;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(HungeryMode.getInstance());
            }).start();
        }
    }
}
