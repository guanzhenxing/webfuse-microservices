package cn.webfuse.framework.config;

import cn.webfuse.framework.config.properties.MvcProperties;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(MvcProperties.class)
public class WebMvcAutoConfig extends AbstractWebMvcConfig {

    @Autowired
    private MvcProperties mvcProperties;


    @Override
    public List<HandlerMethodArgumentResolver> getCustomHandlerMethodArgumentResolverList() {
        return new ArrayList<>();
    }

    @Override
    public List<PropertyEditorRegistrar> getCustomPropertyEditorRegistrarList() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, String> getCustomExceptionMappingDefinitions() {
        return new HashMap<>();
    }
}
