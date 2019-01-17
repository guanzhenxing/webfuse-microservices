package cn.webfuse.framework.config;

import cn.webfuse.framework.web.method.SnakeToCamelServletModelAttributeMethodProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 转换蛇形为驼峰型参数
 */
public class ArgumentSnakeToCamelResolverConfig implements WebMvcConfigurer {
    //***********自定义参数解析处理*************//

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(customModelAttributeMethodProcessor());
    }

    /**
     * 自定义请求参数模型处理
     */
    @Bean
    protected SnakeToCamelServletModelAttributeMethodProcessor customModelAttributeMethodProcessor() {
        return new SnakeToCamelServletModelAttributeMethodProcessor(true);
    }
}
