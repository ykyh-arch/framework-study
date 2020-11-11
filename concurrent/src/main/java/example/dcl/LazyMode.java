package example.dcl;

/**
 * @ClassName: LazyMode
 * @Description: 懒汉式
 * 线程不安全，不能保证实例对象的唯一性，懒加载，性能好。
 * @Author: Uetec
 * @Date: 2020-11-11-14:24
 * @Version: 1.0
 **/
public class LazyMode {
    private static LazyMode instance=null;
    private LazyMode(){
    }
    public synchronized static LazyMode getInstance(){
        if(null==instance)
            instance=new LazyMode();
        return instance;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(LazyMode.getInstance());
            }).start();
        }
    }
}
