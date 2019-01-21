package cn.webfuse.framework.logging;

import cn.webfuse.framework.logging.access.EnableAccessLogger;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableAccessLogger
public @interface EnableLogger {
}
