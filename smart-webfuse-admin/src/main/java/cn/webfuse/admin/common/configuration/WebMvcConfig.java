package cn.webfuse.admin.common.configuration;

import cn.webfuse.framework.i18n.MessageResourceInterceptor;
import cn.webfuse.framework.config.AbstractBaseCustomWebMvcConfiguration;
import cn.webfuse.framework.web.method.CustomPropertyEditorRegistrarBuilder;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class WebMvcConfig extends AbstractBaseCustomWebMvcConfiguration {

    @Override
    protected PropertyEditorRegistrar[] getCustomPropertyEditorRegistrarList() {
        PropertyEditorRegistrar escapeString = CustomPropertyEditorRegistrarBuilder.escapeString();
        return new PropertyEditorRegistrar[]{escapeString};
    }

    @Override
    public void addCustomInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(messageResourceInterceptor());
    }

    @Bean
    MessageResourceInterceptor messageResourceInterceptor() {
        return new MessageResourceInterceptor();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
