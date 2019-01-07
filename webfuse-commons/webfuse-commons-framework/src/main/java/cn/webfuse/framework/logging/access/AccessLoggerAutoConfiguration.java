package cn.webfuse.framework.logging.access;

import cn.webfuse.framework.logging.LoggerInfo;
import cn.webfuse.framework.logging.LoggerWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnClass(LoggerWriter.class)
@Configuration
public class AccessLoggerAutoConfiguration {

    @Bean
    public AccessAbstractLoggerAspect accessLoggerAspect() {
        return new AccessAbstractLoggerAspect();
    }

    @Bean
    @ConditionalOnMissingBean(LoggerWriter.class)
    public LoggerWriter defaultLoggerWriter() {
        return new LoggerWriter() {
            Logger logger = LoggerFactory.getLogger("LOGGER_WRITER");

            @Override
            public void write(LoggerInfo loggerInfo) {
                logger.info(loggerInfo.toString());
            }
        };
    }

}
