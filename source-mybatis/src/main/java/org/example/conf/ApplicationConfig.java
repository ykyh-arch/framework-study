package org.example.conf;

import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @ClassName: ApplicationConfig
 * @Description: 配置类。
 * 数据源配置
 * @Author: Uetec
 * @Date: 2021-01-13-15:03
 * @Version: 1.0
 **/
@Configuration
@MapperScan("org.example.dao")
@ComponentScan("org.example")
public class ApplicationConfig {

    @Bean
    @Autowired
    public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        //日志打印，从日志工厂获取自定义日志，底层通过反射得到传入的日志实现类的构造器再new出具体的日志对象；
        //使用时通过LogFory.getLog（）获取日志；
        //默认：尝试获取，从工厂获取；
        org.apache.ibatis.session.Configuration configuration =
                new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(Log4jImpl.class);
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://120.27.149.128:3306/king?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false");
        driverManagerDataSource.setUsername("King");
        driverManagerDataSource.setPassword("King123");
        return driverManagerDataSource;
    }

}
