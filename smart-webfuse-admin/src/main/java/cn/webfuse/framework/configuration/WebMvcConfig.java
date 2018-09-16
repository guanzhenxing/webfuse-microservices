package cn.webfuse.framework.configuration;

import cn.webfuse.framework.web.method.CustomPropertyEditorRegistrarBuilder;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebMvcConfig extends AbstractBaseCustomWebMvcConfigurer {

    @Override
    protected PropertyEditorRegistrar[] getCustomPropertyEditorRegistrarList() {
        PropertyEditorRegistrar escapeString = CustomPropertyEditorRegistrarBuilder.escapeString();
        return new PropertyEditorRegistrar[]{escapeString};
    }

    @Override
    public void addCustomInterceptors(InterceptorRegistry registry) {

    }
}
