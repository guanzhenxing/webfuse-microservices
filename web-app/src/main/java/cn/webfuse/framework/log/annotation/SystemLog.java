package cn.webfuse.framework.log.annotation;

import java.lang.annotation.*;

/**
 * 系统日志自定义注解
 * <p>
 * 作用于参数或方法上
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String description() default "";
}
