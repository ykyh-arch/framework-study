package example.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
//切面
@Aspect
public class AspectUse {

    //连接点表达式
    /*
    * execution：用于匹配方法执行 join points连接点，最小粒度方法，在aop中主要使用
    * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
    * 这里问号表示当前项可以有也可以没有，其中各项的语义如下：
    * modifiers-pattern：方法的可见性，如public，protected；
    * ret-type-pattern：方法的返回值类型，如int，void等；
    * declaring-type-pattern：方法所在类的全路径名，如com.spring.Aspect；
    * name-pattern：方法名类型，如buisinessService()；
    * param-pattern：方法的参数类型，如java.lang.String；
    * throws-pattern：方法抛出的异常类型，如java.lang.Exception；
    *
    *
    *
    * */


    //切点
//    @Pointcut("execution(protected * example.aop.service.*.*(..))")
    @Pointcut("execution(* example.aop.service.*.*(..))")
    private void pointcutExecution() {
    }

    //切点
    @Pointcut("within(example.aop.service.*)")
    private void pointcutWithin() {
    }

    //切点
    @Pointcut("args(java.lang.String)")
    private void pointcutArgs() {
    }

    //切点
    @Pointcut("@annotation(example.aop.inno.AspectUse)")
    private void pointcutAnnotation() {
    }

    //切点，代理对象
    @Pointcut("this(example.aop.dao.impl.IndexDaoImpl)")
    private void pointcutThis() {
    }

    //切点，目标对象
    @Pointcut("target(example.aop.dao.impl.IndexDaoImpl)")
    private void pointcutTarget() {
    }

    //通知(位置+逻辑)
//    @Before("pointcutWithin()")
//    @Before("pointcutExecution()")
//    @Before("pointcutExecution() && !pointcutArgs()")
//    @Before("pointcutAnnotation()")
    @Before("pointcutThis()")
//    @Before("pointcutTarget()")
    private void before(JoinPoint joinPoint, ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("before");
    }

}
