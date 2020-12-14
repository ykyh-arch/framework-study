package example.mybatis.mapperscan.anno;

import example.mybatis.mapperscan.factorybean.SelfImportBeanDefinitionRegistrar;
import org.mybatis.spring.annotation.MapperScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SelfImportBeanDefinitionRegistrar.class)
public @interface SelfMapperScan {

}
