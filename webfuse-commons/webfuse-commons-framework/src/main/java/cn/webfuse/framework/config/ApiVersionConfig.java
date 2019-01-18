package cn.webfuse.framework.config;

import cn.webfuse.framework.web.version.ApiVersionRequestMappingHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * API版本控制
 */
public class ApiVersionConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        //支持API版本管理RequestMappingHandlerMapping
        RequestMappingHandlerMapping mapping = new ApiVersionRequestMappingHandlerMapping();
        return mapping;
    }

}
