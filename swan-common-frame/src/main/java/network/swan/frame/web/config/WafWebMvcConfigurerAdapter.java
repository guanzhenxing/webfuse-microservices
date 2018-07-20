package network.swan.frame.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import network.swan.frame.web.config.support.WafJsonMapper;
import network.swan.frame.web.mvc.CustomServletModelAttributeMethodProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.Filter;
import java.util.List;

/**
 * 基于SpringMVC注解方式进行配置waf的spring配置。
 * Created by guanzhenxing on 2017/11/3.
 */
@Configuration
@EnableWebMvc
public class WafWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(mappingJackson2HttpMessageConverter());
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
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(wafInterceptor());
    }

    /**
     * 定义Json的转换格式
     *
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = WafJsonMapper.getMapper();
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }


    @Bean
    public Filter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
        return filter;
    }

    /**
     * 添加请求参数解析器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(customModelAttributeMethodProcessor());
    }

    @Bean
    protected CustomServletModelAttributeMethodProcessor customModelAttributeMethodProcessor() {
        return new CustomServletModelAttributeMethodProcessor(true);
    }


}
