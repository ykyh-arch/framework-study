package example.mybatis.mapperscan.factorybean;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: SelfFactoryBean
 * @Description: 自定义FactoryBean。
 * @Author: Uetec
 * @Date: 2020-12-14-16:00
 * @Version: 1.0
 **/
public class SelfFactoryBean implements FactoryBean,InvocationHandler {

    Class clazz;

    public SelfFactoryBean(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object getObject() throws Exception {
        Class[] classes =new Class[]{clazz};
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                classes, this);
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy");
        Method md = proxy.getClass().getInterfaces()[0].getMethod(method.getName(),String.class);
        Select select = md.getDeclaredAnnotation(Select.class);
        System.out.println(select.value()[0]);
        return null;
    }
}
