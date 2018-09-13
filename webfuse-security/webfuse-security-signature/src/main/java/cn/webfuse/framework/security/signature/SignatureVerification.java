package cn.webfuse.framework.security.signature;

import java.lang.annotation.*;

/**
 * 安全校验的注解，一切被此注解的方法或者类都需要被安全校验。
 * 如果是在类上使用此注解，那么该类下的所有的方法的调用都要被校验。
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SignatureVerification {
}
