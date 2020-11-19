package example.ioc.autowired.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ApplicationConfig
 * @Description: 应用配置文件。
 * @Author: Uetec
 * @Date: 2020-11-19-12:04
 * @Version: 1.0
 **/
@Configuration
@ComponentScan(basePackages="example.ioc.autowired")
public class ApplicationConfig {

}
