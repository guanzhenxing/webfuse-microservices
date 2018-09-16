package cn.webfuse.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"cn.webfuse"})
@EnableAsync
public class SmartWebfuseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartWebfuseApplication.class, args);
    }
}
