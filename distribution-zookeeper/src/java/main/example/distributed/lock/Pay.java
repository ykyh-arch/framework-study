package example.distributed.lock;

/**
 * @ClassName: Pay
 * @Description: 支付模块。
 * @Author: Uetec
 * @Date: 2020-12-04-15:29
 * @Version: 1.0
 **/
public class Pay {
    public void pay(){
        System.out.println(Thread.currentThread().getName()+"支付成功");
    }
}
