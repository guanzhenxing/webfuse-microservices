package cn.webfuse.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webfuse.mvc")
@Data
public class WebMvcProperties {

    /**
     * url上蛇形参数转换为驼峰
     */
    private boolean argumentSnakeToCamel = true;


}
