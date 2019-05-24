package cn.webfuse.microservices.centralregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CentralRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentralRegistryApplication.class, args);
    }
}
