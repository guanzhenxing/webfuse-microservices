package cn.webfuse.framework.web.configuration;

import cn.webfuse.framework.exception.handler.DefaultRestfulErrorResolver;
import cn.webfuse.framework.exception.handler.HandlerRestfulExceptionResolver;
import cn.webfuse.framework.web.support.WebFuseJsonMapper;
import cn.webfuse.framework.web.support.bind.CustomServletModelAttributeMethodProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractBaseCustomWebMvcConfigurer extends WebMvcConfigurationSupport {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    /**
     * 定义Json的转换格式
     *
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = WebFuseJsonMapper.getMapper();
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    /**
     * 添加CORS映射
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true).maxAge(3600);
    }

    /**
     * 配置HiddenHttpMethodFilter过滤器
     *
     * @return
     */
    @Bean
    public Filter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }


    @Override
    protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
        ConfigurableWebBindingInitializer initializer = super.getConfigurableWebBindingInitializer();
        initializer.setPropertyEditorRegistrars(getCustomPropertyEditorRegistrarList());
        return initializer;
    }

    protected abstract PropertyEditorRegistrar[] getCustomPropertyEditorRegistrarList();

    /**
     * 添加参数解析
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(customModelAttributeMethodProcessor());
    }

    /**
     * 自定义请求参数模型处理
     *
     * @return
     */
    @Bean
    protected CustomServletModelAttributeMethodProcessor customModelAttributeMethodProcessor() {
        return new CustomServletModelAttributeMethodProcessor(true);
    }


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
        return new AcceptHeaderLocaleResolver();
    }


    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addCustomInterceptors(registry);
    }

    public abstract void addCustomInterceptors(InterceptorRegistry registry);
}
