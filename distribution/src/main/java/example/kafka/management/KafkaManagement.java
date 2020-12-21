package example.kafka.management;

import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import scala.collection.JavaConversions;

import java.util.List;
import java.util.Properties;

/**
 * @ClassName: KafkaManagement
 * @Description: kafka管理命令。
 * @Author: Uetec
 * @Date: 2020-12-21-11:29
 * @Version: 1.0
 **/
public class KafkaManagement {

    public static void main(String[] args) {
        listTopic();
    }
//    创建topic
    public static void createTopic(){
        ZkUtils zkUtils = ZkUtils.apply("192.168.1.49:2181/kafka", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        System.out.println(JaasUtils.isZkSecurityEnabled());
        AdminUtils.createTopic(zkUtils, "kafka-topic2", 1, 1, new Properties(), AdminUtils.createTopic$default$6());
        zkUtils.close();
    }
//    删除topic
    public static  void deleteTopic(){
        ZkUtils zkUtils = ZkUtils.apply("192.168.1.49:2181/kafka", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        AdminUtils.deleteTopic(zkUtils, "kafka-topic2");
        zkUtils.close();
    }
//    列出所有topic
    public  static void listTopic(){
        ZkUtils zkUtils = ZkUtils.apply("192.168.1.49:2181/kafka", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        List<String> list = JavaConversions.seqAsJavaList(zkUtils.getAllTopics());
        for (String s : list) {
            System.out.println(s);
        }
        zkUtils.close();
    }

}
