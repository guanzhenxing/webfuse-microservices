package network.swan.gateway.config;

import network.swan.gateway.filter.AccessFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by guanzhenxing on 2017/7/27.
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }


}

