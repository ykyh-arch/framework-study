package example.dcl;

/**
 * @ClassName: DcL
 * @Description: 双重检查
 * 性能比较好，懒加载，线程的安全性
 * 问题：因为指令重排引起空指针异常，解决方案：volatile
 * @Author: Uetec
 * @Date: 2020-11-11-14:37
 * @Version: 1.0
 **/
public class DcLMode {

//  Connection conn;
//  Socket socket;
    private volatile static DcLMode instance=null;
    private DcLMode(){
        //Socket socket = new Socket();
        //Connection conn = new Connection();
    }

    public  static DcLMode getInstance(){
        if(null==instance)
            synchronized (DcLMode.class){
                if(null==instance)
                   instance=new DcLMode();
            }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                System.out.println(DcLMode.getInstance());
            }).start();
        }
    }
}
