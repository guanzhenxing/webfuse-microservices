package cn.webfuse.framework.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * 添加了Swagger2的配置
 *
 * @author Jesen
 */
public abstract class AbstractCustomSwagger2WebMvcConfiguration extends AbstractBaseCustomWebMvcConfiguration {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
