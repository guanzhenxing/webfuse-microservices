package cn.webfuse.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MicroServiceConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceConfigServiceApplication.class, args);
    }
}
