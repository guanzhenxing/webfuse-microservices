package cn.webfuse.framework.logging.config;

import cn.webfuse.framework.core.kit.mapper.JsonMapper;
import cn.webfuse.framework.logging.LoggerInfo;
import cn.webfuse.framework.logging.LoggerWriter;
import cn.webfuse.framework.logging.access.AccessAbstractLoggerAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty(prefix = "webfuse.logging", value = "enabled", havingValue = "true", matchIfMissing = false)
public class LoggerAutoConfig {

    @Bean
    public AccessAbstractLoggerAspect accessLoggerAspect() {
        return new AccessAbstractLoggerAspect();
    }

    @Bean
    @ConditionalOnMissingBean(LoggerWriter.class)
    public LoggerWriter defaultLoggerWriter() {
        return new LoggerWriter() {
            Logger logger = LoggerFactory.getLogger("LOGGER");

            @Override
            public void write(LoggerInfo loggerInfo) {
                logger.info(JsonMapper.defaultMapper().toJson(loggerInfo));
            }
        };
    }

}
