package cn.webfuse.framework.config;

import cn.webfuse.framework.config.properties.MvcProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(MvcProperties.class)
@ServletComponentScan(basePackages = "cn.webfuse")
public class WebMvcAutoConfig implements WebMvcConfigurer {

    @Autowired
    private MvcProperties mvcProperties;


    @Bean
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
