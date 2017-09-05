package network.swan.ms.configuration.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign的日志配置。<br/>
 * 默认的Feign是不记录日志的，所以这边需要设置日志的级别<br/>
 * <li>NONE：不记录日志</li>
 * <li>BASIC：仅记录请求方法、URL、响应状态代码以及执行时间</li>
 * <li>HEADERS：记录BASIC级别的基础上，记录请求和响应的header</li>
 * <li>FULL：记录请求和响应的header，body和元数据</li>
 * <p>
 * Created by guanzhenxing on 2017/8/5.
 */
@Configuration
public class FeignLogConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
