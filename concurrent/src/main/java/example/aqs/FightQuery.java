package example.aqs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: FightQuery
 * @Description: 查询飞机票。CountDownLatch 计数器，查询航班、APP--->三个线程到不同的公司查询--》result
 * @Author: Uetec
 * @Date: 2020-11-13-14:39
 * @Version: 1.0
 **/
public class FightQuery {

    private static List<String> company= Arrays.asList("东方航空","南方航空","海南航空");
    private static List<String> fightList=new ArrayList<>();

    public static void main(String[] args) throws InterruptedException{
        //起始地
        String origin="BJ";
        //目的地
        String dest="SH";
        Thread[] threads=new Thread[company.size()];
        //count=3，有线程访问-1
        CountDownLatch latch=new CountDownLatch(company.size());

        for (int i = 0; i < threads.length; i++) {
            String name=company.get(i);
            threads[i]=new Thread(()->{
                System.out.printf("%s，查询从%s到%s的机票\n",name,origin,dest);
                //随机产生票数
                int val=new Random().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(val);
                    fightList.add(name+"查询的票数："+val);
                    System.out.printf("%s公司查询成功！\n",name);
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        latch.await();
        System.out.println("==============查询结果如下：================");
        fightList.forEach(System.out::println);
    }
}
