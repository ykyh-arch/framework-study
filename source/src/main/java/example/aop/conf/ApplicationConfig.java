package example.aop.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName: ApplicationConfig
 * @Description: 应用配置文件。
 * @Author: Uetec
 * @Date: 2020-11-19-12:04
 * @Version: 1.0
 **/
@Configuration
@ComponentScan("example.aop")
//开启AspectJ
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApplicationConfig {

}
