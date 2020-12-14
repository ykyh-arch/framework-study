package example.mybatis.mapperscan.conf;

import example.mybatis.mapperscan.anno.SelfMapperScan;
import example.mybatis.mapperscan.factorybean.SelfImportBeanDefinitionRegistrar;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @ClassName: ApplicationConfig
 * @Description: 应用配置文件。
 * @Author: Uetec
 * @Date: 2020-11-19-12:04
 * @Version: 1.0
 **/
//@Configuration
@Configurable
@ComponentScan(basePackages="example.mybatis")
//mapper文件自动扫描，可以将一个接口转变成对象
//@MapperScan("example.mybatis")
//@Import(SelfImportBeanDefinitionRegistrar.class)
@SelfMapperScan
public class ApplicationConfig {

    @Bean
    @Autowired
    //Bean 的依赖
    public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://120.27.149.128:3306/king?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false");
        driverManagerDataSource.setUsername("King");
        driverManagerDataSource.setPassword("King123");
        return driverManagerDataSource;
    }

}
