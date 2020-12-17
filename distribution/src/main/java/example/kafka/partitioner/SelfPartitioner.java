package example.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @ClassName: SelfPartitioner
 * @Description: 自定义分区迭代器。
 * @Author: Uetec
 * @Date: 2020-12-17-15:44
 * @Version: 1.0
 **/
public class SelfPartitioner implements Partitioner {

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        System.out.println("自定义分区迭代器");
        if(key == null){
            return 0;
        }
        return 1;
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}
