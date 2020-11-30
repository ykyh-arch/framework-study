package example.proxy.handler;

import java.lang.reflect.Method;

/**
 * 自定义一个InvocationHandlerInf
 */
public interface SelfInvocationHandlerInf {

    /**
     * @param method 目标方法
     * @return
     */
    public Object invoke(Method method) throws Exception;
}
