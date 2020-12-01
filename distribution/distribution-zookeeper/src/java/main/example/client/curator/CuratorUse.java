package example.client.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @ClassName: CuratorUse
 * @Description: Curator使用。
 * @Author: Uetec
 * @Date: 2020-11-27-17:15
 * @Version: 1.0
 **/
public class CuratorUse {

    public static void main(String[] args) throws Exception {
        CuratorFramework client =
                CuratorFrameworkFactory.newClient("192.168.1.49:2181",15000,15000,
                        new RetryNTimes(3,1000));
       //启用客户端，流式操作
        client.start();
        //相关API
        //创建Znode
        //client.create().withMode(CreateMode.PERSISTENT).forPath("/data","1".getBytes());
        //NodeCache 对象
        NodeCache nodeCache = new NodeCache(client,"/data");
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("数据改变了...");
            }
        });

        //原生API支持
        client.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("数据改变了...");
            }
        }).forPath("/data");
        System.in.read();

    }




}
