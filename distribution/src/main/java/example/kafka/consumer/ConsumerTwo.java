package example.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

/**
 * @ClassName: Consumer
 * @Description: 消费者。
 *               主动拉取消息
 * @Author: Uetec
 * @Date: 2020-12-17-15:28
 * @Version: 1.0
 **/
public class ConsumerTwo {

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
        final KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        //订阅主题
        consumer.subscribe(Collections.singletonList("kafka-topic1"),new ConsumerRebalanceListener() {
            //分区再均衡配置
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                System.out.println("分区再均衡之前");
                //同步提交下，因为最后一次
                consumer.commitSync();
            }

            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                System.out.println("分区再均衡之后");
                for (TopicPartition partition : partitions) {
                    System.out.println("新分区："+ partition.partition());
                }
            }
        });

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
