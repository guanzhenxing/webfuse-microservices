package network.swan.ms.config;

import feign.Feign;
import network.swan.frame.annotation.IgnoreDuringSpringScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 禁用Feign中的Hystrix，留着为某个Client使用。使用方法：
 * <code>
 *     @FeignClient(name="xxx",configuration=FeignDisableHystrixConfiguration.class)
 *     public interface XXXFeignClient{
 *
 *     }
 * </code>
 * 注意：该类不应该在主应用程序上下文的@ComponentScan中
 * Created by guanzhenxing on 2017/8/5.
 */
@Configuration
@IgnoreDuringSpringScan
public class FeignDisableHystrixConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

}

