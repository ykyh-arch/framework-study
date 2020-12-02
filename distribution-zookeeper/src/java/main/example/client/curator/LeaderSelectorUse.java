package example.client.curator;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.RetryNTimes;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CuratorUse
 * @Description: LeaderSelectorUse使用。
 *               领导着选举
 * @Author: Uetec
 * @Date: 2020-11-27-17:15
 * @Version: 1.0
 **/
public class LeaderSelectorUse {

    public static void main(String[] args) throws Exception {

        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderSelector> leaderSelectores = Lists.newArrayList();

        for (int i = 0; i < 10; i++) {
            CuratorFramework client =
                    CuratorFrameworkFactory.newClient("192.168.1.49:2181",
                            15000,15000,
                            new RetryNTimes(3,1000));
            client.start();
            clients.add(client);

            LeaderSelector leaderSelector =new LeaderSelector(client, "/leaderSelector", new LeaderSelectorListener() {
                @Override
                public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                    //基于Lock实现，会不停的选举
                    System.out.println("leader："+curatorFramework);
                    TimeUnit.SECONDS.sleep(5);
                }

                @Override
                public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

                }
            });
            leaderSelector.start();
            leaderSelectores.add(leaderSelector);

        }

        System.in.read();

        //关闭
        for (CuratorFramework client: clients) {
            client.close();
        }

        for (LeaderSelector leaderSelector: leaderSelectores) {
            leaderSelector.close();
        }

    }

}
