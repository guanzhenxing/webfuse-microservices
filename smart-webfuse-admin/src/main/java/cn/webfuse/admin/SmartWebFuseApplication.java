package cn.webfuse.admin;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"cn.webfuse"})
@EnableAsync
@EnableSwagger2Doc
public class SmartWebFuseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartWebFuseApplication.class, args);
    }
}
