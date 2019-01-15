package cn.webfuse.framework.config;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * 自定义的WebMvc自动化配置
 *
 * @author Jesen
 */
@Configuration
public class CustomWebMvcAutoConfiguration extends AbstractBaseCustomWebMvcConfiguration {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    protected PropertyEditorRegistrar[] getCustomPropertyEditorRegistrarList() {
        return new PropertyEditorRegistrar[0];
    }

    @Override
    public void addCustomInterceptors(InterceptorRegistry registry) {

    }
}
