package example.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ApplicationConfig
 * @Description: 配置类。
 * @Author: Uetec
 * @Date: 2020-12-08-16:57
 * @Version: 1.0
 **/
@Configuration
public class ApplicationConfig {

//    @Bean
//    public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
//        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory(8080);
//        return tomcatServletWebServerFactory;
//    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost",5672);
        //我这里直接在构造方法传入了
        //connectionFactory.setHost();
        //connectionFactory.setPort();
        connectionFactory.setUsername("distribution");
        connectionFactory.setPassword("distribution");
        connectionFactory.setVirtualHost("/distribution");
        //是否开启消息确认机制
        //connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    //消息监听器容器，提供了相关API操作消息
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory){
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//        //消息的确认方式
//        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        //绑定队列
//        simpleMessageListenerContainer.addQueueNames("queue1","queue2");
//        simpleMessageListenerContainer.addQueues(new Queue("queue4",true));
//        simpleMessageListenerContainer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                //监听消息
//            }
//        });
//        return simpleMessageListenerContainer;
//    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory());
        //消息确认方式
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //消息预取
        simpleRabbitListenerContainerFactory.setPrefetchCount(1);
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate =new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

}
