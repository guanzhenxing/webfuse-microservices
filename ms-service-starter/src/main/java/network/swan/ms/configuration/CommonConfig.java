package network.swan.ms.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * 公共的配置
 * Created by guanzhenxing on 2017/8/5.
 */
@Configuration
public class CommonConfig {

    /**
     * 具有负载均衡的RestTemplate
     *
     * @return RestTemplate实例
     */
    @Bean
    @LoadBalanced
    public RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    /**
     * 不具有负载均衡的RestTemplate
     *
     * @return RestTemplate实例
     */
    @Primary
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}


