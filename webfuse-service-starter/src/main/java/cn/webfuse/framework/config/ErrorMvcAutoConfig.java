package cn.webfuse.framework.config;

import cn.webfuse.framework.config.properties.WebMvcProperties;
import cn.webfuse.framework.core.kit.mapper.JsonMapper;
import cn.webfuse.framework.exception.handle.impl.DefaultRestfulErrorController;
import cn.webfuse.framework.exception.handle.impl.DefaultRestfulErrorConverter;
import cn.webfuse.framework.exception.handle.impl.DefaultRestfulErrorResolver;
import cn.webfuse.framework.exception.handle.impl.DefaultRestfulExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@AutoConfigureBefore(WebMvcAutoConfig.class)
@EnableConfigurationProperties(WebMvcProperties.class)
@ServletComponentScan(basePackages = "cn.webfuse")
public class ErrorMvcAutoConfig {

    public static final String PROPERTIES_PREFIX = "webfuse.mvc";

    @Autowired
    private WebMvcProperties webMvcProperties;

    /**
     * errorViewResolvers
     */
    @Autowired(required = false)
    private List<ErrorViewResolver> errorViewResolvers;

    /**
     * serverProperties
     */
    private final ServerProperties serverProperties;

    /**
     * 构造函数
     *
     * @param serverProperties serverProperties
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ErrorMvcAutoConfig(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "restful-exception-handle.enabled", matchIfMissing = true)
    public DefaultRestfulErrorController defaultRestfulErrorController(ErrorAttributes errorAttributes) {
        DefaultRestfulErrorController defaultRestfulErrorController = new DefaultRestfulErrorController(errorAttributes, this.serverProperties.getError(),
                this.errorViewResolvers);

        defaultRestfulErrorController.setDefaultDocument(webMvcProperties.getRestfulExceptionHandle().getDefaultDocument());

        return defaultRestfulErrorController;
    }

    /**
     * 异常处理解析器
     */
    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "restful-exception-handle.enabled", matchIfMissing = true)
    public DefaultRestfulExceptionHandler defaultHandlerRestfulExceptionResolver() {

        DefaultRestfulErrorResolver defaultRestfulErrorResolver = new DefaultRestfulErrorResolver();
        defaultRestfulErrorResolver.setLocaleResolver(localeResolver());
        defaultRestfulErrorResolver.setExceptionMappingDefinitions(getExceptionMappingDefinitions());
        defaultRestfulErrorResolver.setDefaultDocument(webMvcProperties.getRestfulExceptionHandle().getDefaultDocument());
        defaultRestfulErrorResolver.setShowDeveloperMessage(webMvcProperties.getRestfulExceptionHandle().isShowDeveloperMessage());

        DefaultRestfulErrorConverter defaultRestfulErrorConverter = new DefaultRestfulErrorConverter();


        DefaultRestfulExceptionHandler handlerRestfulExceptionResolver =
                new DefaultRestfulExceptionHandler(defaultRestfulErrorConverter, defaultRestfulErrorResolver);
        handlerRestfulExceptionResolver.setOrder(-1);

        return handlerRestfulExceptionResolver;
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
}
