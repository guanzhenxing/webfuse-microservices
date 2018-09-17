package cn.webfuse.framework.mongo.option;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MongoOptionAutoConfiguration.class})
public @interface EnableMongoOption {
}
