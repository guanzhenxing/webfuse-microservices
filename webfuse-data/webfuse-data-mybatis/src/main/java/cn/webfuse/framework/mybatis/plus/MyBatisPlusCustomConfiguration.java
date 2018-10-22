package cn.webfuse.framework.mybatis.plus;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;


/**
 * mybatis-plus的自定义配置
 */
@Configuration
@EnableConfigurationProperties(MybatisPlusProperties.class)
@AutoConfigureBefore(MybatisPlusAutoConfiguration.class)
public class MyBatisPlusCustomConfiguration {

    private final MybatisPlusProperties properties;


    public MyBatisPlusCustomConfiguration(MybatisPlusProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void doCustomConfig() {
        //设置banner为false，不打印
        this.properties.getGlobalConfig().setBanner(false);
    }

    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev", "test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }


    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
