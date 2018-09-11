package cn.webfuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SmartWebfuseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartWebfuseApplication.class, args);
    }
}
