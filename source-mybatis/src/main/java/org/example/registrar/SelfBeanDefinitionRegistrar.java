package org.example.registrar;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName: SelfRegistrar
 * @Description: 注册器。
 * @Author: Uetec
 * @Date: 2021-01-19-16:26
 * @Version: 1.0
 **/
public class SelfBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //BeanDefinition 增强
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) registry.getBeanDefinition("userService");
        //根据类型自动装配
        beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        registry.registerBeanDefinition("userService",beanDefinition);
    }
}
