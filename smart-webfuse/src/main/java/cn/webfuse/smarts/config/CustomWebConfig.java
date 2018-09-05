package cn.webfuse.smarts.config;

import cn.webfuse.framework.configuration.AbstractBaseCustomWebMvcConfigurer;
import cn.webfuse.framework.web.support.CustomPropertyEditorRegistrarBuilder;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.Map;

@Configuration
@EnableWebMvc
public class CustomWebConfig extends AbstractBaseCustomWebMvcConfigurer {

    @Override
    protected PropertyEditorRegistrar[] getCustomPropertyEditorRegistrarList() {

        PropertyEditorRegistrar escapeString = CustomPropertyEditorRegistrarBuilder.escapeString();

        return new PropertyEditorRegistrar[]{escapeString};
    }

    @Override
    public Map<String, String> getCustomExceptionMappingDefinitions() {
        return null;
    }

    @Override
    public void addCustomInterceptors(InterceptorRegistry registry) {

    }
}
