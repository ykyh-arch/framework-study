package example.dcl;

/**
 * @ClassName: LazySysnMode
 * @Description: 懒汉式加同步
 * 线程安全：不安全，懒加载，性能：synchronized 退化到了串行执行。解决方案：DCL
 * @Author: Uetec
 * @Date: 2020-11-11-14:31
 * @Version: 1.0
 **/
public class LazySysnMode {
    private static LazySysnMode instance=null;
    private LazySysnMode(){
    }
    public  static LazySysnMode getInstance(){
        if(null==instance)
            synchronized (LazySysnMode.class){
                instance=new LazySysnMode();
            }
        return instance;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(LazySysnMode.getInstance());
            }).start();
        }
    }
}
