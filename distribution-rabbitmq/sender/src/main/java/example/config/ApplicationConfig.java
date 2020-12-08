package example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

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
        //是否开启消息确认机制
        //connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate =new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

//    @Bean
//    public DirectExchange defaultExchange() {
//        return new DirectExchange("exchange1");
//    }

//    @Bean
//    public Queue queue() {
//        //名字是否持久化
//        return new Queue("queue1", true);
//    }

//    @Bean
//    public Binding binding() {
//        //绑定一个队列  to: 绑定到哪个交换机上面  with：绑定的路由建（routingKey）
//        return BindingBuilder.bind(queue()).to(defaultExchange()).with("debug.user.b");
//    }

}
