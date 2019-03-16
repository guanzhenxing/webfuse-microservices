package cn.webfuse.framework.config;

import cn.webfuse.framework.config.properties.WebMvcProperties;
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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

@Configuration
@EnableConfigurationProperties(WebMvcProperties.class)
@ServletComponentScan(basePackages = "cn.webfuse")
public class WebMvcAutoConfig {

    public static final String PROPERTIES_PREFIX = "webfuse.mvc";

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


//类似这样的可以添加一些配置
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//
//        };
//    }


}
