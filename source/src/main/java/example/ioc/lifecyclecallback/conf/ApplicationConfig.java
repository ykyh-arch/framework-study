package example.ioc.lifecyclecallback.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName: ApplicationConfig
 * @Description: 应用配置文件。
 * @Author: Uetec
 * @Date: 2020-11-19-12:04
 * @Version: 1.0
 **/
@Configuration
@ComponentScan(basePackages="example.ioc.lifecyclecallback")
@ImportResource("classpath:applicationContext-lifecyclecallback.xml")
//包扫描排除子包以及子类
/*@ComponentScan(value="example.ioc.lifecyclecallback",
        excludeFilters={@ComponentScan.Filter(type = FilterType.REGEX,
                pattern = "example.ioc.lifecyclecallback.service.*")})*/
public class ApplicationConfig {

}
