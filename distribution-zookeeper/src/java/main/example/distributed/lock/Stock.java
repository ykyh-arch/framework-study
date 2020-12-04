package example.distributed.lock;

/**
 * @ClassName: Stock
 * @Description: 库存模块。
 * @Author: Uetec
 * @Date: 2020-12-04-15:25
 * @Version: 1.0
 **/
public class Stock {

    static Integer num = 1;
    //减库存
    public boolean reduceStock(){
        if(num >0){
            System.out.println("减库存");
            num--;
            return true;
        }
        return false;
    }
}
