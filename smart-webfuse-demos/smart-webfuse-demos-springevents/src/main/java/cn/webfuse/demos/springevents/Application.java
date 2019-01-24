package cn.webfuse.demos.springevents;

import cn.webfuse.demos.springevents.synchronous.publisher.CustomSpringEventPublisher;
import cn.webfuse.demos.springevents.userdemo.model.UserBean;
import cn.webfuse.demos.springevents.userdemo.publisher.UserPublisher;
import org.apache.commons.collections.map.SingletonMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication(scanBasePackages = {"cn.webfuse"})
@RestController
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserPublisher userPublisher;

    @Autowired
    private CustomSpringEventPublisher customSpringEventPublisher;

    @GetMapping("/test_user_event")
    public Map<String, String> testUserEvent() {
        UserBean userBean = new UserBean();
        userBean.setUsername("jesen");
        userBean.setPassword("13");
        userPublisher.register(userBean);
        return new SingletonMap("result", "success");
    }

    @GetMapping("/test_spring_event")
    public Map<String, String> testCustomSpringEvent() {
        customSpringEventPublisher.publishEvent("CustomSpringEvent");
        customSpringEventPublisher.publishGenericAppEvent("GenericStringSpringAppEvent");
        customSpringEventPublisher.publishGenericEvent("GenericStringSpringEvent", true);
        return new SingletonMap("result", "success");
    }


}

