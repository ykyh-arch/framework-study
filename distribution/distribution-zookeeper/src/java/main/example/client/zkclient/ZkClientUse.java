package example.client.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;

/**
 * @ClassName: ZkClientUse
 * @Description: 客户端使用。
 * @Author: Uetec
 * @Date: 2020-11-27-15:40
 * @Version: 1.0
 **/
public class ZkClientUse {
    public static void main(String[] args) throws IOException {
        ZkClient zkClient =
                new ZkClient("192.168.1.49:2181",15000,15000,new SerializableSerializer());
        //相关API
        zkClient.subscribeDataChanges("/data", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                //可以监视每次数据改变的状态
                System.out.println("数据改变了..." + s);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("数据删除了...");
            }
        });

        System.in.read();
    }
}

class ZkClientTest{
    public static void main(String[] args) throws IOException {
        ZkClient zkClient =
                new ZkClient("192.168.1.49:2181",15000,15000,new SerializableSerializer());
        //相关API
        zkClient.writeData("/data","123");

    }
}
