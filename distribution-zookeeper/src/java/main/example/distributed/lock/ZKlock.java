package example.distributed.lock;

import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName: ZKlock
 * @Description: ZK实现分布式锁。
 *               思路：1、创建临时顺序节点对应每个系统（用户线程）
 *                    2、查询父节点下的子节点当中最小的节点，对应的系统获得锁
 *                    3、采用ZK的watch机制，监听上一个节点事件，如果前一个节点删除，对应下一个节点系统获取锁
 * @Author: Uetec
 * @Date: 2020-12-04-15:59
 * @Version: 1.0
 **/
public class ZKlock implements Lock {

    private ThreadLocal<ZooKeeper> zk =new ThreadLocal<>();

    private String lockName="/LOCK";
    private ThreadLocal<String> currentNode = new ThreadLocal<>();

    //初始化
    public void init(){
        try {
            zk.set(new ZooKeeper("192.168.1.49:2181", 15000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    //初始默认监视
                }
            }));
            if(zk.get().exists(lockName,false)==null){
                zk.get().create(lockName,new byte[1],ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //加锁
    public void lock(){
        init();
        if(tryLock()){
            System.out.println(Thread.currentThread().getName()+"已经获取到了锁");
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    //尝试获取锁，阻塞方法
    public boolean tryLock(){
        String nodeName=lockName + "/zk_";
        try {
            //临时顺序节点，格式：/LOCK/zk_1
            currentNode.set(zk.get().create(nodeName,new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL));
            //格式：zk_1、zk_2
            List<String> childNodes = zk.get().getChildren(lockName,false);
            Collections.sort(childNodes);
            //当前节点是否为最小节点
            if(currentNode.equals(lockName+"/"+childNodes.get(0))){
                return true;
            }else{//监视
                String currentNodeSimpleName = currentNode.get().substring(currentNode.get().lastIndexOf("/")+1);
                int currentNodeIndex =childNodes.indexOf(currentNodeSimpleName);
                //以下需要被阻塞
                CountDownLatch countDownLatch = new CountDownLatch(1);
                zk.get().exists(lockName + "/" + childNodes.get(currentNodeIndex - 1), new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        //节点被删除
                        if(Event.EventType.NodeDeleted==event.getType()){
                            countDownLatch.countDown();
                            System.out.println(Thread.currentThread().getName()+"节点被唤醒");
                        }
                    }
                });
                countDownLatch.await();
                System.out.println("节点被阻塞");
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    //释放锁
    public void unlock(){
        try {
            //删除，忽略版本
            zk.get().delete(currentNode.get(),-1);
            currentNode.set(null);
            zk.get().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
