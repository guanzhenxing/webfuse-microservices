package cn.webfuse.framework.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

import java.lang.invoke.MethodHandles;

public class ApplicationContextListener implements ApplicationListener {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof ApplicationEnvironmentPreparedEvent) {   //初始化环境变量开始
            LOGGER.info("========== 初始化环境变量开始 ==========");
        } else if (event instanceof ApplicationPreparedEvent) {     // 初始化环境变量完成
            LOGGER.info("========== 初始化环境变量完成 ==========");
        } else if (event instanceof ContextRefreshedEvent) { // 应用刷新
            LOGGER.info("========== 应用刷新 ==========");
        } else if (event instanceof ApplicationReadyEvent) {// 应用已启动完成
            LOGGER.info("========================");
            String server = ((ApplicationReadyEvent) event).getSpringApplication().getAllSources().iterator().next().toString();
            LOGGER.info("系统[{}]启动完成!!!", server.substring(server.lastIndexOf(".") + 1));
            LOGGER.info("========================");
        } else if (event instanceof ContextStartedEvent) { // 应用启动，需要在代码动态添加监听器才可捕获
            LOGGER.info("========== 应用启动 ==========");
        } else if (event instanceof ContextStoppedEvent) { // 应用停止
            LOGGER.info("========== 应用停止 ==========");
        } else if (event instanceof ContextClosedEvent) { // 应用关闭
            LOGGER.info("========== 应用关闭 ==========");
        } else {
        }

    }
}
