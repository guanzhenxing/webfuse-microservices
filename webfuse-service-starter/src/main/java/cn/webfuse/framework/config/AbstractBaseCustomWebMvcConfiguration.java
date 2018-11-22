package cn.webfuse.framework.config;

import cn.webfuse.framework.web.WebFuseJsonMapper;
import cn.webfuse.framework.web.method.CustomServletModelAttributeMethodProcessor;
import cn.webfuse.framework.web.version.ApiVersionRequestMappingHandlerMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.Filter;
import java.util.List;


@Configuration
public abstract class AbstractBaseCustomWebMvcConfiguration extends WebMvcConfigurationSupport {

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


    /**
     * PropertyEditorRegistrar列表。主要是用来做请求参数转换的
     *
     * @return
     */
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
     * 设置版本管理
     *
     * @return
     */
    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new ApiVersionRequestMappingHandlerMapping();
        mapping.setOrder(0);
        mapping.setInterceptors(getInterceptors());
        return mapping;
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
