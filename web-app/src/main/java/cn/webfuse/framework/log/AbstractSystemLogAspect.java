package cn.webfuse.framework.log;

import cn.webfuse.framework.log.annotation.SystemLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.Date;

//@Aspect
//@Component

/**
 * 抽象的系统日志切面
 */
public abstract class AbstractSystemLogAspect {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    protected static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal beginTime");


    /**
     * 切点，注解方式
     */
    @Pointcut("@annotation(cn.webfuse.framework.log.annotation.SystemLog)")
    public void log() {
    }

    /**
     * 前置通知（在方法执行前返回）
     *
     * @param joinPoint 切点
     */
    @Before("log()")
    public void before(JoinPoint joinPoint) {
        beginTimeThreadLocal.set(new Date()); //线程绑定变量（该数据只有当前请求的线程可见）
        doBefore(joinPoint);
    }

    /**
     * 处理前置通知，可以在子类中进行处理
     *
     * @param joinPoint
     */
    protected abstract void doBefore(JoinPoint joinPoint);

    @After("log()")
    public void after(JoinPoint joinPoint) {
        doAfter(joinPoint);
    }

    /**
     * 处理后置通知，可以在子类中进行处理
     *
     * @param joinPoint
     */
    protected abstract void doAfter(JoinPoint joinPoint);


    /**
     * 获得方法的描述
     *
     * @param joinPoint 切点
     * @return 返回注解中的description描述
     * @throws Exception
     */
    protected static String getMethodDescription(JoinPoint joinPoint) throws Exception {

        String targetName = joinPoint.getTarget().getClass().getName();//获取目标类名
        Class targetClass = Class.forName(targetName);//生成类对象
        Method[] methods = targetClass.getMethods(); //获取该类中的方法
        String methodName = joinPoint.getSignature().getName();//获取方法名
        Object[] arguments = joinPoint.getArgs();//获取相关参数

        String description = "";

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzList = method.getParameterTypes();
            if (clazzList.length != arguments.length) {
                continue; //比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载
            }
            description = method.getAnnotation(SystemLog.class).description();
        }
        return description;
    }


}
