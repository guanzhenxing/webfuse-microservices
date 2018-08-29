package cn.webfuse.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MicroserviceGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayServiceApplication.class, args);
    }
}
