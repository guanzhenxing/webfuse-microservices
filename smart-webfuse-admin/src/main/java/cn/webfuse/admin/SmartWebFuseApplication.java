package cn.webfuse.admin;

import cn.webfuse.framework.logging.access.EnableAccessLogger;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"cn.webfuse"})
@EnableAsync
@EnableAccessLogger
@EnableSwagger2Doc
public class SmartWebFuseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartWebFuseApplication.class, args);
    }
}
