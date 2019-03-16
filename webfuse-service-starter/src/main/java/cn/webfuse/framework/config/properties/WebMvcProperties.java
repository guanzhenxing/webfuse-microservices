package cn.webfuse.framework.config.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private ApiVersion apiVersion = new ApiVersion();

    /**
     * Restful异常处理
     */
    private RestfulExceptionHandle restfulExceptionHandle = new RestfulExceptionHandle();

    /**
     * cors
     */
    private Cors cors = new Cors();

    @Data
    @NoArgsConstructor
    public static class ApiVersion {
        /**
         * 是否使用API版本控制
         */
        private boolean enabled = true;
        /**
         * API版本控制前缀正则表达式
         */
        private String prefix;
    }

    @Data
    @NoArgsConstructor
    public static class RestfulExceptionHandle {

        /**
         * 是否使用Restful异常处理
         */
        private boolean enabled = true;

        /**
         * 默认的错误文档
         */
        private String defaultDocument;

        /**
         * 显示开发者错误信息
         */
        private boolean showDeveloperMessage = false;

        /**
         * 异常处理匹配
         */
        private List<Mapping> mappings = new ArrayList<>();

        @Data
        @NoArgsConstructor
        public static class Mapping {
            private String clazz;
            private int status = 500;
            private String code = "INTERNAL SERVER ERROR";
            private String message = "";
            private String developerMessage = "";
            private String document = "";
        }

    }


    @Data
    @NoArgsConstructor
    public static class Cors {
        /**
         * 是否使用CORS
         */
        private boolean enabled = true;

        /**
         * 跨域信息
         */
        private Map<String, RegistrationConfig> registrationConfig = new ConcurrentHashMap<>();

        /**
         * 描述 : 跨域信息
         */
        @Data
        public static class RegistrationConfig {
            /**
             * 描述 : 扫描地址
             */
            private String mapping = "/**";

            /**
             * 描述 : 允许cookie
             */
            private Boolean allowCredentials = true;

            /**
             * 描述 : 允许的域
             */
            private String allowedOrigins = "*";

            /**
             * 描述 : 允许的方法
             */
            private String allowedMethods = "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE";

            /**
             * 描述 : 允许的头信息
             */
            private String allowedHeaders = "*";

        }
    }
}
