package example.distributed.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ZkConfig
 * @Description: 配置中心类。
 * @Author: Uetec
 * @Date: 2020-12-04-17:40
 * @Version: 1.0
 **/
public class ZkConfig {

    //缓存
    private Map<String,Object> map =new HashMap<>();
    private static final String CONFIG_PRE = "/CONFIG";
    CuratorFramework client;

    public ZkConfig() {
        client = CuratorFrameworkFactory.newClient("192.168.1.49:2181"
                ,15000,15000,new RetryNTimes(3,1000));
        client.start();
        init();
    }

    public void init(){
        //update cache
        try {
            List<String> childNames = client.getChildren().forPath(CONFIG_PRE);
            for (String childName : childNames) {
                String value = new String(client.getData().forPath(CONFIG_PRE+"/"+childName));
                map.put(childName,value);
            }
            //添加监听器 监听孩子节点数据变化
            PathChildrenCache watcher = new PathChildrenCache(client,CONFIG_PRE,true);
            watcher.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                    //子节点变化
                    String path = event.getData().getPath();
                    if(path.startsWith(CONFIG_PRE)){
                        path = path.replace(CONFIG_PRE+"/","");
                        if(PathChildrenCacheEvent.Type.CHILD_ADDED.equals(event.getType())
                                || PathChildrenCacheEvent.Type.CHILD_UPDATED.equals(event.getType())){
                            map.put(path,new String(event.getData().getData()));
                        }else{
                            map.remove(path);
                        }
                    }
                }
            });
            watcher.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //创建节点、更新节点
    public void save(String name,String value){
        String configFullPath = CONFIG_PRE +"/" + name;
        try {
            Stat stat = client.checkExists().forPath(configFullPath);
            if(stat ==null){
                client.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(configFullPath,value.getBytes());
            }else{
                client.setData().forPath(configFullPath,value.getBytes());
            }
            map.put(name,value);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String name){
        return (String)map.get(name);
    }

}
