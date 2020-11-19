package example.ioc.injection.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName: ApplicationConfig
 * @Description: 应用配置文件。
 * @Author: Uetec
 * @Date: 2020-11-19-12:04
 * @Version: 1.0
 **/
@Configuration
@ComponentScan(basePackages="example")
//注解与XML混合使用
//@ImportResource("classpath:applicationContext-xml.xml")
public class ApplicationConfig {

}
