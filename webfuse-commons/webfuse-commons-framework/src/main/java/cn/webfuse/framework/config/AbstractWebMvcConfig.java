package cn.webfuse.framework.config;

import cn.webfuse.framework.exception.handler.DefaultRestfulErrorResolver;
import cn.webfuse.framework.exception.handler.HandlerRestfulExceptionResolver;
import cn.webfuse.framework.web.method.CustomPropertyEditorRegistrarBuilder;
import cn.webfuse.framework.web.method.CustomServletModelAttributeMethodProcessor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWebMvcConfig implements WebMvcConfigurer {


    //***********自定义参数解析处理*************//

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(customModelAttributeMethodProcessor());
        argumentResolvers.addAll(getCustomHandlerMethodArgumentResolverList());
    }

    /**
     * 自定义请求参数模型处理
     */
    @Bean
    protected CustomServletModelAttributeMethodProcessor customModelAttributeMethodProcessor() {
        return new CustomServletModelAttributeMethodProcessor(true);
    }

    public abstract List<HandlerMethodArgumentResolver> getCustomHandlerMethodArgumentResolverList();


    @Bean
    public ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();

        List<PropertyEditorRegistrar> propertyEditorRegistrars = new ArrayList();
        propertyEditorRegistrars.add(CustomPropertyEditorRegistrarBuilder.escapeString());
        propertyEditorRegistrars.addAll(getCustomPropertyEditorRegistrarList());

        PropertyEditorRegistrar[] array = new PropertyEditorRegistrar[propertyEditorRegistrars.size()];
        propertyEditorRegistrars.toArray(array);

        initializer.setPropertyEditorRegistrars(array);
        return initializer;
    }

    public abstract List<PropertyEditorRegistrar> getCustomPropertyEditorRegistrarList();


    //***********异常处理*************//

    /**
     * 异常处理解析器
     */
    @Bean
    public HandlerRestfulExceptionResolver handlerRestfulExceptionResolver() {
        HandlerRestfulExceptionResolver handlerRestfulExceptionResolver = new HandlerRestfulExceptionResolver();
        handlerRestfulExceptionResolver.setOrder(-1);
        handlerRestfulExceptionResolver.setRestfulErrorResolver(defaultRestfulErrorResolver());
        return handlerRestfulExceptionResolver;
    }

    /**
     * 默认的错误解析
     */
    @Bean
    public DefaultRestfulErrorResolver defaultRestfulErrorResolver() {
        DefaultRestfulErrorResolver defaultRestfulErrorResolver = new DefaultRestfulErrorResolver();
        defaultRestfulErrorResolver.setLocaleResolver(localeResolver());
        defaultRestfulErrorResolver.setExceptionMappingDefinitions(getExceptionMappingDefinitions());
        return defaultRestfulErrorResolver;
    }

    /**
     * 异常处理方式的定义
     */
    public Map<String, String> getExceptionMappingDefinitions() {
        Map<String, String> exceptionMappingDefinitions = new HashMap<>();
        exceptionMappingDefinitions.put("Throwable", "{\"status\":500}");
        exceptionMappingDefinitions.put("RuntimeException", "{\"status\":500}");
        exceptionMappingDefinitions.put("cn.webfuse.framework.exception.AbstractBizException", "{\"status\":500,\"code\":\"INTERNAL SERVER ERROR\",\"message\":\"\",\"developerMessage\":\"\"}");

        Map<String, String> customExceptionMappingDefinitions = getCustomExceptionMappingDefinitions();
        if (customExceptionMappingDefinitions != null) {
            exceptionMappingDefinitions.putAll(customExceptionMappingDefinitions);
        }
        return exceptionMappingDefinitions;
    }

    /**
     * 获得自定义的异常处理mapping
     */
    public abstract Map<String, String> getCustomExceptionMappingDefinitions();

    /**
     * 定义本地化
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();    //通过检验HTTP请求的accept-language头部来解析区域。
    }


}
