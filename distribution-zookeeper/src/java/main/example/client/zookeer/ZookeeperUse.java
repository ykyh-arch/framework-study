package example.client.zookeer;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * @ClassName: ZookeeperUse
 * @Description: 原生Zookeer使用。相关API
 * @Author: Uetec
 * @Date: 2020-11-27-14:39
 * @Version: 1.0
 **/
public class ZookeeperUse {

    public static void main(String[] args) throws Exception {
        //连接， 默认Watcher，session时间设置长一点，避免网络原因导致无法连接
        ZooKeeper client = new ZooKeeper("192.168.1.49:2181", 15000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("连接到Zk服务器...");
            }
        });
        Stat stat = new Stat();
        String data = new String(client.getData("/data", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(Event.EventType.NodeDataChanged.equals(event.getType())){
                    //只执行一次
                    System.out.println("数据发生了改变...");
                }

            }
        },stat));
        //默认watch
        //String data = new String(client.getData("/data",true,stat));

        client.getData("/data", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("回调结果...");
            }
        }, null);

        //创建节点，所有权限
        client.create("/data_1","1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        System.in.read();
    }


}

