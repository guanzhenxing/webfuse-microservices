package cn.webfuse.demos.mybatis.config;

import cn.webfuse.data.mybatis.pageable.PageInterceptor;
import cn.webfuse.demos.mybatis.interceptor.SimpleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfig {

    @Bean
    public SimpleInterceptor simpleInterceptor() {
        return new SimpleInterceptor();
    }

    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", "cn.webfuse.data.mybatis.pageable.db.MySqlDialect");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

//    @Bean
//    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
//        return configuration -> {
//
//        };
//    }
}
