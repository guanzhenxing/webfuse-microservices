package cn.webfuse.framework.logging.access;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * 启用访问日志
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(AccessLoggerAutoConfig.class)
public @interface EnableAccessLogger {
}
