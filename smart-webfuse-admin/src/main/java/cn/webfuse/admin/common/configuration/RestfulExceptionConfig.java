package cn.webfuse.admin.common.configuration;

import cn.webfuse.framework.exception.configuration.AbstractBaseRestfulExceptionConfig;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RestfulExceptionConfig extends AbstractBaseRestfulExceptionConfig {
    @Override
    public Map<String, String> getCustomExceptionMappingDefinitions() {
        return null;
    }
}
