package example.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

/**
 * @ClassName: Consumer
 * @Description: 消费者。
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
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        //订阅主题
        consumer.subscribe(Collections.singletonList("kafka-topic1"));
        //主动拉取消息
        while (true){
            ConsumerRecords<String, String> poll = consumer.poll(500);
            for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
                //消息内容
                System.out.println("分区："+stringStringConsumerRecord.partition()
                +"，偏移量："+stringStringConsumerRecord.offset()
                +"，key:"+stringStringConsumerRecord.key()
                +"，value:"+stringStringConsumerRecord.value());
            }
        }
    }
}
