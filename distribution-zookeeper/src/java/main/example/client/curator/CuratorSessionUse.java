package example.client.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @ClassName: CuratorUse
 * @Description: CuratorSessionUse使用。
 *               原生的ZK中，当客户端由于网络原因中断，服务端SESSION会失效，使得客户端的节点操作以及监听器会失效
 *               Curator框架解决了这一问题
 * @Author: Uetec
 * @Date: 2020-11-27-17:15
 * @Version: 1.0
 **/
public class CuratorSessionUse {

    public static void main(String[] args) throws Exception {
        CuratorFramework client =
                CuratorFrameworkFactory.newClient("192.168.1.49:2181",15000,15000,
                        new RetryNTimes(3,1000));
       //启用客户端，流式操作
        client.start();
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            //连接状态改变
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                //网络中断
                if(connectionState == ConnectionState.LOST){
                    try {
                        if(client.getZookeeperClient().blockUntilConnectedOrTimedOut()){
                            //执行任务
                            doTask();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        doTask();
        System.in.read();

    }

    private static void doTask() {
        //对Znode相关操作
    }


}
