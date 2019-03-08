package cn.webfuse.framework.config;

import cn.webfuse.framework.core.kit.mapper.JsonMapper;
import cn.webfuse.framework.exception.handler.RestfulErrorConverter;
import cn.webfuse.framework.exception.handler.RestfulErrorResolver;
import cn.webfuse.framework.exception.handler.impl.DefaultRestfulErrorConverter;
import cn.webfuse.framework.exception.handler.impl.DefaultRestfulErrorResolver;
import cn.webfuse.framework.web.method.SnakeToCamelServletModelAttributeMethodProcessor;
import cn.webfuse.framework.web.version.ApiVersionRequestMappingHandlerMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(WebMvcProperties.class)
@ServletComponentScan(basePackages = "cn.webfuse")
public class WebMvcAutoConfig {

    private static final String PROPERTIES_PREFIX = "webfuse.mvc";

    @Autowired
    private WebMvcProperties webMvcProperties;

    /**
     * 转换蛇形为驼峰型参数
     */
    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "argument.snake-to-camel")
    public WebMvcConfigurer snakeToCamelArgumentResolverConfig() {
        return new WebMvcConfigurer() {

            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
                argumentResolvers.add(customModelAttributeMethodProcessor());
            }

            @Bean
            protected SnakeToCamelServletModelAttributeMethodProcessor customModelAttributeMethodProcessor() {
                return new SnakeToCamelServletModelAttributeMethodProcessor(true);
            }
        };
    }

    /**
     * API版本控制
     */
    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "api-version.enabled", matchIfMissing = true)
    public WebMvcRegistrations apiVersionConfig() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                ApiVersionRequestMappingHandlerMapping mapping = new ApiVersionRequestMappingHandlerMapping();
                mapping.setVersionPrefix(webMvcProperties.getApiVersion().getPrefix());
                return mapping;
            }
        };
    }

    /**
     * 异常处理解析器
     */
    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "restful-exception-handle.enabled", matchIfMissing = true)
    public RestfulErrorResolver defaultRestfulErrorResolver() {
        DefaultRestfulErrorResolver defaultRestfulErrorResolver = new DefaultRestfulErrorResolver();
        defaultRestfulErrorResolver.setLocaleResolver(localeResolver());
        defaultRestfulErrorResolver.setExceptionMappingDefinitions(getExceptionMappingDefinitions());

        return defaultRestfulErrorResolver;
    }

    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "restful-exception-handle.enabled", matchIfMissing = true)
    public RestfulErrorConverter defaultRestfulErrorConverter() {
        return new DefaultRestfulErrorConverter();
    }


    private Map<String, String> getExceptionMappingDefinitions() {
        Map<String, String> exceptionMappingDefinitions = new HashMap<>();
        exceptionMappingDefinitions.put("java.lang.Throwable", "{\"status\":500}");
        exceptionMappingDefinitions.put("java.lang.RuntimeException", "{\"status\":500}");
        exceptionMappingDefinitions.put("cn.webfuse.framework.exception.AbstractBizException", "{\"status\":500,\"code\":\"INTERNAL SERVER ERROR\",\"message\":\"\",\"developerMessage\":\"\"}");

        Map<String, String> customExceptionMappingDefinitions = getCustomExceptionMappingDefinitions();
        if (customExceptionMappingDefinitions != null) {
            exceptionMappingDefinitions.putAll(customExceptionMappingDefinitions);
        }
        return exceptionMappingDefinitions;

    }

    private Map<String, String> getCustomExceptionMappingDefinitions() {
        WebMvcProperties.RestfulExceptionHandle restfulExceptionHandle = webMvcProperties.getRestfulExceptionHandle();
        Map<String, String> res = new HashMap<>();
        restfulExceptionHandle.getMappings().stream().forEach(mapping -> {
            String key = mapping.getClazz();
            String value = JsonMapper.defaultMapper().toJson(mapping);
            res.put(key, value);
        });
        return res;
    }

    /**
     * 定义本地化
     */
    @Bean
    public LocaleResolver localeResolver() {
        //通过检验HTTP请求的accept-language头部来解析区域。
        return new AcceptHeaderLocaleResolver();
    }


//类似这样的可以添加一些配置
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//
//        };
//    }


}
