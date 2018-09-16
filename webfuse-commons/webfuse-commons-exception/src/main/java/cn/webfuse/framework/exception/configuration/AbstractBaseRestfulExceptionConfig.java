package cn.webfuse.framework.exception.configuration;

import cn.webfuse.framework.exception.handler.DefaultRestfulErrorResolver;
import cn.webfuse.framework.exception.handler.HandlerRestfulExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.HashMap;
import java.util.Map;

@Configuration
public abstract class AbstractBaseRestfulExceptionConfig {

    /**
     * 异常处理解析器
     *
     * @return
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
     *
     * @return
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
     *
     * @return
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

    public abstract Map<String, String> getCustomExceptionMappingDefinitions();

    /**
     * 定义本地化
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();    //通过检验HTTP请求的accept-language头部来解析区域。
    }
}
