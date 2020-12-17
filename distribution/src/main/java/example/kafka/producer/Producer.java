package example.kafka.producer;

import example.kafka.partitioner.SelfPartitioner;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @ClassName: Producer
 * @Description: 生产者。
 * @Author: Uetec
 * @Date: 2020-12-17-15:10
 * @Version: 1.0
 **/
public class Producer {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        //指定kafka服务器地址，如果是集群可以指定多个，
        //但是就算只指定一个他也会去集群环境下寻找其他的节点地址
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        //key序列化器（用于指定分区用的）
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        //value序列化器
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        //自定义分区迭代器
        properties.setProperty("partitioner.class", SelfPartitioner.class.getName());
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String, String> stringStringProducerRecord =
                new ProducerRecord<String, String>("kafka-topic1",1,"key","hello world");
//        new ProducerRecord<String, String>("kafka-topic1",null,"hello world");
        //同步返回结果
        Future<RecordMetadata> send = kafkaProducer.send(stringStringProducerRecord);
        RecordMetadata recordMetadata = send.get();
        System.out.println("偏移量："+recordMetadata.offset()+"，所在分区："+recordMetadata.partition());

        //异步获取结果
        kafkaProducer.send(stringStringProducerRecord, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(null!=exception){
                    exception.printStackTrace();
                }
                //返回数据
                if(null!=metadata){
                    System.out.println(metadata);
                }
            }
        });

        kafkaProducer.flush();
        kafkaProducer.close();
    }

}
