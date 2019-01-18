package cn.webfuse.framework.logging;

import cn.webfuse.framework.logging.access.EnableAccessLogger;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(EnableAccessLogger.class)
public @interface EnableLogger {
}
