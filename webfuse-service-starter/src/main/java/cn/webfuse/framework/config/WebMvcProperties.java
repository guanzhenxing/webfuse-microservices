package cn.webfuse.framework.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

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


}
