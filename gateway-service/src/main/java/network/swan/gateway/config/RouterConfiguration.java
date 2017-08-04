package network.swan.gateway.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由匹配规则的配置文件
 * Created by guanzhenxing on 2017/7/26.
 */
@Configuration
public class RouterConfiguration {

    /**
     * 将serviceName-v1映射到/v1/serviceName
     *
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }

}

