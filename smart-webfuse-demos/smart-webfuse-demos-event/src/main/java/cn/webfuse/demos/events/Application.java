package cn.webfuse.demos.events;

import cn.webfuse.demos.events.publisher.CustomSpringEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"cn.webfuse"})
@RestController
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    CustomSpringEventPublisher customSpringEventPublisher;


    @RequestMapping("/register")
    public String register() {
        customSpringEventPublisher.doStuffAndPublishAnEvent("fuck....");
        return "success";
    }


}

