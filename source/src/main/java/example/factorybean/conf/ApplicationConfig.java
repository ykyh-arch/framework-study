package example.factorybean.conf;

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
@ComponentScan(basePackages="example.factorybean")
@ImportResource("classpath:applicationContext-factorybean.xml")
public class ApplicationConfig {

}
