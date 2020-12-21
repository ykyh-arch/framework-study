package example.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

/**
 * @ClassName: Consumer
 * @Description: 独立消费者。
 *               消费者主动从指定的分区下消费信息。
 * @Author: Uetec
 * @Date: 2020-12-17-15:28
 * @Version: 1.0
 **/
public class IndependentConsumer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        //key 反序列化
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer",StringDeserializer.class.getName());
        //指定消费者群组
        properties.setProperty("group.id","1");
        //提交方式：手动提交
        properties.setProperty("enable.auto.commit","false");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        //分配分区
        List<TopicPartition> list = new ArrayList<TopicPartition>();
        list.add(new TopicPartition("kafka-topic1",0));
        //指定主题下的所有分区
        /*List<PartitionInfo> partitionInfos = consumer.partitionsFor("kafka-topic1");
        //过滤需要的分区
        for (PartitionInfo partitionInfo : partitionInfos) {
            list.add(new TopicPartition(partitionInfo.topic(),partitionInfo.partition()));
        }*/
        consumer.assign(list);

        try{
            //主动拉取消息
            while (true){
                ConsumerRecords<String, String> poll = consumer.poll(500);
                for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
                    //消息内容
                    System.out.println("分区："+stringStringConsumerRecord.partition()
                            +"，偏移量："+stringStringConsumerRecord.offset()
                            +"，key:"+stringStringConsumerRecord.key()
                            +"，value:"+stringStringConsumerRecord.value());

                    //正常情况异步提交
                    consumer.commitAsync();
                    //优雅的关闭 退出消费者群组
                    consumer.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //当程序中断时同步提交
                consumer.commitSync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //关闭当前消费者
                consumer.close();
            }
        }
    }

}
