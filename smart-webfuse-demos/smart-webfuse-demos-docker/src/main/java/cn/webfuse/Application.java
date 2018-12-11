package cn.webfuse;

import org.apache.commons.collections.map.SingletonMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication(scanBasePackages = {"cn.webfuse"})
@RestController
@RequestMapping("/test")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${demos.value1}")
    private String value1;

    @GetMapping("/")
    public Map<String, String> test() {
        return new SingletonMap("v1", value1);
    }

}
