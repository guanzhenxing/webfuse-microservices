package cn.webfuse.framework.configuration;

import cn.webfuse.framework.web.interceptor.MessageResourceInterceptor;
import cn.webfuse.framework.web.support.CustomPropertyEditorRegistrarBuilder;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.Map;

@Configuration
@EnableWebMvc
public class BaseCustomWebMvcConfigurer extends AbstractBaseCustomWebMvcConfigurer {

    @Override
    protected PropertyEditorRegistrar[] getCustomPropertyEditorRegistrarList() {
        PropertyEditorRegistrar escapeString = CustomPropertyEditorRegistrarBuilder.escapeString();
        return new PropertyEditorRegistrar[]{escapeString};
    }

    /**
     * 异常的解析配置
     *
     * @return
     */
    @Override
    public Map<String, String> getCustomExceptionMappingDefinitions() {
        return null;
    }

    @Override
    public void addCustomInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MessageResourceInterceptor());
    }
}
