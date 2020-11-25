package example.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: SelfInvocationHandler
 * @Description: 处理类。
 * @Author: Uetec
 * @Date: 2020-11-25-15:53
 * @Version: 1.0
 **/
public class SelfInvocationHandler implements InvocationHandler {

    Object target;

    public SelfInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy 代理对象
     * @param method 目标对象中的目标方法
     * @param args 目标方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk invocationHandler");
        return method.invoke(target,args);
    }

}
