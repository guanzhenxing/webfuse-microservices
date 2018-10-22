package cn.webfuse.admin;

import cn.webfuse.framework.logging.access.EnableAccessLogger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("cn.webfuse.**.mapper.**")
@SpringBootApplication(scanBasePackages = {"cn.webfuse"})
@EnableAsync
@EnableAccessLogger
public class SmartWebFuseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartWebFuseApplication.class, args);
    }
}
