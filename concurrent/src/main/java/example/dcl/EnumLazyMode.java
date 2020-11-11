package example.dcl;

/**
 * @ClassName: EnumLazyMode
 * @Description: 枚举模式加延迟加载
 * @Author: Uetec
 * @Date: 2020-11-11-15:06
 * @Version: 1.0
 **/
public class EnumLazyMode {
    private EnumLazyMode(){
    }
    //延迟加载
    private enum EnumHolder{
        INSTANCE;
        private  EnumLazyMode instance=null;
        EnumHolder(){
            instance=new EnumLazyMode();
        }
        private EnumLazyMode getInstance(){
            return instance;
        }
    }
    public static EnumLazyMode  getInstance(){
        return EnumHolder.INSTANCE.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(EnumLazyMode.getInstance());
            }).start();
        }
    }
}
