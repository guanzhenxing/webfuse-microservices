package network.swan.frame.db.mybatis.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义的MyBatis配置
 * Created by guanzhenxing on 2017/11/12.
 */
@Configuration
public class MyBatisConfig {

    @Bean
    ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            configuration.setMapUnderscoreToCamelCase(true);    //自动将以下划线方式命名的数据库字段映射到Java对象的驼峰式
        };
    }


}
