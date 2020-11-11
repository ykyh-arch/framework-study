package example.dcl;

/**
 * @ClassName: HolderMode
 * @Description: 内部类获取单例，广泛的一种单例模式
 * @Author: Uetec
 * @Date: 2020-11-11-14:50
 * @Version: 1.0
 **/
public class HolderMode {
    private HolderMode(){
    }
    private static class Holder{
        private static HolderMode instance=new HolderMode();
    }

    public static HolderMode getInstance(){
        return Holder.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(HolderMode.getInstance());
            }).start();
        }
    }

}
