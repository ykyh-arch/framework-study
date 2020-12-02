package example.client.curator;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CuratorUse
 * @Description: CuratorSessionUse使用。
 *               领导着选举
 * @Author: Uetec
 * @Date: 2020-11-27-17:15
 * @Version: 1.0
 **/
public class LeaderLatchUse {

    public static void main(String[] args) throws Exception {

        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> leaderLatches = Lists.newArrayList();

        for (int i = 0; i < 10; i++) {
            CuratorFramework client =
                    CuratorFrameworkFactory.newClient("192.168.1.49:2181",
                            15000,15000,
                            new RetryNTimes(3,1000));
            client.start();
            clients.add(client);

            LeaderLatch leaderLatch =new LeaderLatch(client,"/leaderLatch","leader#"+i);
            leaderLatch.start();
            leaderLatches.add(leaderLatch);

        }

        TimeUnit.SECONDS.sleep(5);

        for (LeaderLatch leaderLatch: leaderLatches) {
            //会创建临时顺序节点进行领导着选举，取最小值的为leader
            if(leaderLatch.hasLeadership()){
                System.out.println("leader："+leaderLatch.getId());
            }
        }


        System.in.read();

        //关闭
        for (CuratorFramework client: clients) {
            client.close();
        }
        for (LeaderLatch client: leaderLatches) {
            client.close();
        }

    }

}
