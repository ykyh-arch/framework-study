package example.dcl;

/**
 * @ClassName: EnumMode
 * @Description: 枚举模式
 * @Author: Uetec
 * @Date: 2020-11-11-14:59
 * @Version: 1.0
 **/
public enum EnumMode {
    INSTANCE;
    public static EnumMode getInstance(){
        return INSTANCE;
    }
}
class TestEnumMode{
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(EnumMode.getInstance());
            }).start();
        }
    }
}