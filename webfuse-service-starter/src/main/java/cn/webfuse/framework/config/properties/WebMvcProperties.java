package cn.webfuse.framework.config.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * webfuse框架mvc的基本配置
 */
@ConfigurationProperties(prefix = "webfuse.mvc")
@Data
public class WebMvcProperties {

    /**
     * 在url上蛇形参数转换为驼峰
     */
    private boolean argumentSnakeToCamel = false;

    /**
     * API版本控制
     */
    private final ApiVersion apiVersion = new ApiVersion();

    @Data
    @NoArgsConstructor
    public static class ApiVersion {
        /**
         * 是否使用API版本控制
         */
        private boolean enable = true;
        /**
         * API版本控制前缀正则表达式
         */
        private String prefix;
    }


}
