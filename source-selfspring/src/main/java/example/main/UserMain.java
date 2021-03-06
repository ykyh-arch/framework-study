package example.main;


import example.service.UserService;
import example.service.impl.UserServiceImpl;
import example.utils.AnnotationConfigApplicationContext;
import example.utils.BeanFactory;

public class UserMain {
    public static void main(String[] args) {

        //硬编码，没有交给容器处理
//        UserServiceImpl service = new UserServiceImpl();
//        service.find();//java.lang.NullPointerException

        /**
         * XML版的注入
         */
        BeanFactory beanFactory = new BeanFactory("appicationContext-inject.xml");
        UserService service = (UserService) beanFactory.getBean("service");
        service.find();

        /**
         * 注解版的注入
         */
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("example.service.impl");

    }
}
