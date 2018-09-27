package cn.webfuse.framework.mybatis.plus;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * mybatis-plus的自定义配置
 */
@Configuration
@EnableConfigurationProperties(MybatisPlusProperties.class)
@AutoConfigureBefore(MybatisPlusAutoConfiguration.class)
public class MyBatisPlusCustomConfiguration {

    private final MybatisPlusProperties properties;

    private final Interceptor[] interceptors;

    private final ResourceLoader resourceLoader;

    private final DatabaseIdProvider databaseIdProvider;

    private final List<ConfigurationCustomizer> configurationCustomizers;

    private final ApplicationContext applicationContext;

    public MyBatisPlusCustomConfiguration(MybatisPlusProperties properties,
                                          ObjectProvider<Interceptor[]> interceptorsProvider,
                                          ResourceLoader resourceLoader,
                                          ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                                          ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider,
                                          ApplicationContext applicationContext) {
        this.properties = properties;
        this.interceptors = interceptorsProvider.getIfAvailable();
        this.resourceLoader = resourceLoader;
        this.databaseIdProvider = databaseIdProvider.getIfAvailable();
        this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void doCustomConfig() {
        this.properties.getGlobalConfig().setBanner(false); //设置banner为false，不打印
    }


}
