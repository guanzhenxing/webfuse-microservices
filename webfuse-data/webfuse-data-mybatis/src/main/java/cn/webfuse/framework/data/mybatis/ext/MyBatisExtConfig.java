package cn.webfuse.framework.data.mybatis.ext;

import cn.webfuse.framework.data.mybatis.ext.camelcase.CamelCaseMappingWrapperFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisExtConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new CamelCaseMappingWrapperFactory());
    }

}
