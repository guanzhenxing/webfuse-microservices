package cn.webfuse.microservices.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/")
@Slf4j
public class TestController {

    @Value("${server.port}")
    private int port;

    @GetMapping("get_time")
    public String getTime() {
        int random = RandomUtils.nextInt(1, 10);
        try {
            log.info("will sleep {}", random * 1000L);
            Thread.sleep(random * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "The current time is " + new Date().toString() + "(answered by service running on " + port + ")";
    }

    @Value("${myName:hing}")
    private String myName;

    @GetMapping("/my_name")
    public String getMyName() {
        return myName;
    }


}
