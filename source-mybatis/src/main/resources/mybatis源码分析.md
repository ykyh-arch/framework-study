##Mybatis源码分析

###mybatis的源码分析

首先mybatis的源码分为两种情况：

1.单独的mybaits

2.和spring整合的mybatis的源码

这两种情况下的源码分析会有点不同，比如如果是分析mybatis + spring的模式那么mybaits的开始就是从Spring初始化的时候开始。

本文主要针对这种情况来分析mybatis的流程。

首先考虑spring + mybatis的时候他们的关联点有哪些？

1.@MapperScan

2.@Bean SqlSessionFactoryBean

如果你精通spring或者你去看一下MapperScan的源码就会就会知道他所利用就是spring当中的Import和 ImportBeanDefinitionRegistrar 技术来对 spring 进行了扩展，在对上匕—下 @BeanSqlSessionFactoryBean 就会知道Spring 会首先去执行ImportBeanDefinitionRegistrar当中的registerBeanDefinitions方法.关于这一块的代码在上次讲 spring源码的时候以及分析过，故而直接过了。

###mybatis的一级缓存的各种问题

spring 当中为什么失效？

因为mybatis和spring的集成包当中扩展了一个类SqlSessionTemplate，这个类在spring容器启动的时候被注入给了这个mapper（mapperFactoryBean），这个类替代了原来的DefulatSqlSession , SqlSessionTemplate当中的所有查询方法不是直接查询，而是经过一个代理对象，代理增强了查询方法，主要是关闭了session。