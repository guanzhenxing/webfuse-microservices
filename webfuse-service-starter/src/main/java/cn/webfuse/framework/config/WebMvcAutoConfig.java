package cn.webfuse.framework.config;

import cn.webfuse.framework.config.properties.WebMvcProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@EnableConfigurationProperties(WebMvcProperties.class)
@ServletComponentScan(basePackages = "cn.webfuse")
public class WebMvcAutoConfig implements BeanFactoryAware, Ordered {

    private static final String PROPERTIES_PREFIX = "cn.webfuse.web";

    @Autowired
    private WebMvcProperties webMvcProperties;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }


    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "argument.snake-to-camel", matchIfMissing = true)
    public ArgumentSnakeToCamelResolverConfig snakeToCamelArgumentResolverConfig() {
        return new ArgumentSnakeToCamelResolverConfig();
    }

    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "api-version", matchIfMissing = true)
    public ApiVersionConfig apiVersionConfig() {
        return new ApiVersionConfig();
    }


//    @Bean
//    public RestfulExceptionConfig restfulExceptionConfig() {
//        return new RestfulExceptionConfig() {
//            @Override
//            public Map<String, String> getCustomExceptionMappingDefinitions() {
//                return null;
//            }
//        };
//    }


}
