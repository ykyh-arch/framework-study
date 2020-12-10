package example.config;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ApplicationConfig
 * @Description: 配置类。
 * @Author: Uetec
 * @Date: 2020-12-08-16:57
 * @Version: 1.0
 **/
@Configuration
public class ApplicationConfig {

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory(8081);
        return tomcatServletWebServerFactory;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost",5672);
        //我这里直接在构造方法传入了
        //connectionFactory.setHost();
        //connectionFactory.setPort();
        connectionFactory.setUsername("distribution");
        connectionFactory.setPassword("distribution");
        connectionFactory.setVirtualHost("/distribution");
        //是否开启消息确认机制，发送方确认（生产者是否将消息成功发送到交换机）与失败回调（交换机是否将消息成功路由到队列）
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate =new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //发送方确认回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            //附加参数、确认、失败原因
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println(ack);
                System.out.println(cause);
                System.out.println(correlationData);
            }
        });
        //开启失败回调
        rabbitTemplate.setMandatory(true);
        //失败回调，交换机无法成功路由到队列会调用，注意：如果交换机绑定了备用交换机（一般都是fanout型），则不会
        //调用回调，除非备用交换机出故障了
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                //message ：消息内容 + 消息配置
                System.out.println(message);
                System.out.println(replyCode);
                System.out.println(replyText);
                System.out.println(exchange);
                System.out.println(routingKey);
            }
        });
        //自定义消息转化器
        rabbitTemplate.setMessageConverter(new MessageConverter() {
            //发送消息
            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                messageProperties.setContentType("text/plain");
                messageProperties.setContentEncoding("UTF-8");
                Message msg = new Message(JSON.toJSONBytes(object),messageProperties);
                System.out.println("调用了消息转化器");
                return msg;
            }
            //接收消息
            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return null;
            }
        });
        return rabbitTemplate;
    }

//    @Bean
//    public DirectExchange directExchange() {
//        Map<String,Object> map = new HashMap<>();
//        //备用交换机
//        map.put("alternate-exchange","exchange2_backup");
//        return new DirectExchange("exchange2",true,false,map);
//    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange2",true,false,null);
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange("deadexchange",true,false,null);
    }

    @Bean
    public Queue queue4() {
        Map map =new HashMap();
        //绑定死信交换机，死信交换机重定向路由键
        map.put("x-dead-letter-exchange","deadexchange");
        map.put( "x-dead-letter-routing-key","queue5.key");
        //名字是否持久化
        return new Queue("queue4", true,false,false,map);
    }

    @Bean
    public Queue queue5() {
        return new Queue("queue5", true,false,false,null);
    }

    @Bean
    public Binding binding4() {
        //绑定一个队列  to: 绑定到哪个交换机上面  with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue4()).to(topicExchange()).with("queue4.*");
    }

    @Bean
    public Binding binding5() {
        //绑定一个队列  to: 绑定到哪个交换机上面  with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue5()).to(deadExchange()).with("queue5.key");
    }

}
