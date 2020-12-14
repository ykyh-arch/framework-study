package example.mybatis.mapperscan.factorybean;

import example.mybatis.mapperscan.dao.IndexDao;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName: SelfImportBe
 * @Description: 自定义ImportBeanDefinitionRegistrar。
 *               可以用来注册bean到spring容器里，人为干预
 * @Author: Uetec
 * @Date: 2020-12-14-16:26
 * @Version: 1.0
 **/
public class SelfImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //获取bd
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(IndexDao.class);
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) builder.getBeanDefinition();
//        System.out.println(beanDefinition.getBeanClassName());
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("example.mybatis.mapperscan.dao.IndexDao");
        beanDefinition.setBeanClass(SelfFactoryBean.class);
        //向容器中注册一个bd，支持程序员手动扩展bd
        registry.registerBeanDefinition("indexDao",beanDefinition);
    }
}
