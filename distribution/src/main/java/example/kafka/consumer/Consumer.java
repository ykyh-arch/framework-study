package example.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName: Consumer
 * @Description: 消费者。
 *               主动拉取消息
 * @Author: Uetec
 * @Date: 2020-12-17-15:28
 * @Version: 1.0
 **/
public class Consumer {

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
        //订阅主题
        consumer.subscribe(Collections.singletonList("kafka-topic1"));

        Map<TopicPartition, OffsetAndMetadata> offset =
                new HashMap<TopicPartition, OffsetAndMetadata>();
        // OffsetAndMetadata:第一个参数为你要提交的偏移量，第二个参数可以选择性的传入业务ID可以拿来确定。
        // 这次提交这里我直接提交偏移量为0，那么会导致下个消费者或者说分区再均衡之后再来读取这个分区的数据会从第一条开始读取。
        offset.put(new TopicPartition("kafka-topic1", 1), new OffsetAndMetadata(0, "ID"));

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
                    //consumer.commitAsync();
                    //指定偏移量提交参数为map集合，key为指定的主题下的分区，value 为你要提交的偏移量
                    consumer.commitSync(offset);
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
