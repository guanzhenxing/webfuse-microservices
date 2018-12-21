package cn.webfuse.framework.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * 客户端配置。配置Ribbon，Feign等
 */
@Configuration
public class ClientSideConfiguration {

    /**
     * 负载均衡的RestTemplate。使用方法为：
     * <p>
     *
     * <code>
     * {@literal @}Autowired<br/>
     * {@literal @}LoadBalanced<br/>
     * private RestTemplate loadBalanced;
     * </code>
     */
    @LoadBalanced
    @Bean
    RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    /**
     * 常规的RestTemplate。使用方法为：
     * <p>
     *
     * <code>
     * {@literal @}Autowired<br/>
     * private RestTemplate loadBalanced;
     * </code>
     */
    @Primary
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
