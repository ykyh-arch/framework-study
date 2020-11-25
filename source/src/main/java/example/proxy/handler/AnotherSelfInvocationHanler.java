package example.proxy.handler;

import java.lang.reflect.Method;

/**
 * @ClassName: AnotherSelfInvocationHanler
 * @Description: 自定义处理类。
 * @Author: Uetec
 * @Date: 2020-11-25-17:29
 * @Version: 1.0
 **/
public class AnotherSelfInvocationHanler implements SelfInvocationHandlerInf{
    //目标对象
    Object target;

    public AnotherSelfInvocationHanler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Method method) {
        try {
            return method.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
