package cn.webfuse.admin.common.configuration;

import cn.webfuse.framework.config.AbstractBaseRestfulExceptionConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RestfulExceptionConfiguration extends AbstractBaseRestfulExceptionConfiguration {
    @Override
    public Map<String, String> getCustomExceptionMappingDefinitions() {
        return null;
    }
}
