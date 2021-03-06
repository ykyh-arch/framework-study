package example.aop.aspectj;

import example.aop.dao.IndexDao;
import example.aop.dao.impl.IndexDaoImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//切面
@Aspect("perthis(this(example.aop.dao.impl.IndexDaoImpl))")
@Scope("prototype")
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

    /*
     * Introductions
     * defaultImpl 默认实现
     * value 对应实现的包全路径名
     *
     * */
//    @DeclareParents(value="example.aop.dao.impl.*", defaultImpl= IndexDaoImpl.class)
//    public static IndexDao indexDao;


    //切点
//    @Pointcut("execution(protected * example.aop.service.*.*(..))")
    @Pointcut("execution(* example.aop.service.*.*(..))")
    private void pointcutExecution() {
    }

    //切点
//    @Pointcut("within(example.aop.service.*)")
    @Pointcut("within(example.aop.dao.impl.*)")
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
//    @Before("pointcutThis()")
//    @Before("pointcutTarget()")
    private void before(JoinPoint joinPoint) {
        System.out.println("before");
    }

//    @After("pointcutExecution()")
    private void after() {
        System.out.println("after");
    }

    /* 环绕通知
     * ProceedingJoinPoint 正在处理的连接点
     *
     *
     */
    @Around("pointcutWithin()")
    private void pointcutAround(ProceedingJoinPoint pjp) {
        System.out.println("AspectUse hashCode: " + this.hashCode());
        //获取连接点参数
        Object[] args = pjp.getArgs();
        if(args !=null && args.length>0){
            for (int i = 0; i < args.length; i++) {
                //处理连接点参数
                args[i]+=" is invoke";
            }
        }
        System.out.println("before");
        try {
            //重新赋值后交给连接点处理
            pjp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after");

    }
}
