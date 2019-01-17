package cn.webfuse.framework.config;

import cn.webfuse.framework.config.properties.MvcProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(MvcProperties.class)
@ServletComponentScan(basePackages = "cn.webfuse")
public class WebMvcAutoConfig implements WebMvcConfigurer {

    private static final String PROPERTIES_PREFIX = "cn.webfuse";

    @Autowired
    private MvcProperties mvcProperties;


    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_PREFIX, name = "argument.snake-to-camel", matchIfMissing = true)
    public ArgumentSnakeToCamelResolverConfig snakeToCamelArgumentResolverConfig() {
        return new ArgumentSnakeToCamelResolverConfig();
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
