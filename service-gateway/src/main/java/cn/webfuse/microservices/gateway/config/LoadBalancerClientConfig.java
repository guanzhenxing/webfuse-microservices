package cn.webfuse.microservices.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * 客户端配置。配置Ribbon，Feign等
 */
@Configuration
public class LoadBalancerClientConfig {
    /**
     * 负载均衡的RestTemplate。
     */
    @LoadBalanced
    @Bean(name = "loadBalanced")
    RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    /**
     * 常规的RestTemplate。
     */
    @Primary
    @Bean(name = "restTemplate")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

