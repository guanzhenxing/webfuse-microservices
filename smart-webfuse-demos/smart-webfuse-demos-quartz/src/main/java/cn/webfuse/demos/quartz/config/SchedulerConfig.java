package cn.webfuse.demos.quartz.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {


}
