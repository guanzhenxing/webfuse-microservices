package cn.webfuse.demos.mybatis.config;

import cn.webfuse.demos.mybatis.interceptor.SimpleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    @Bean
    public SimpleInterceptor simpleInterceptor() {
        return new SimpleInterceptor();
    }

//    @Bean
//    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
//        return configuration -> {
//
//        };
//    }
}
