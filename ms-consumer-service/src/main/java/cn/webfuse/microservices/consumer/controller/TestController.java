package cn.webfuse.microservices.consumer.controller;

import cn.webfuse.microservices.consumer.service.TestRibbonHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RefreshScope
public class TestController {

    @Autowired

    private TestRibbonHystrixService testRibbonHystrixService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/get_ribbon_hystrix")
    public String getTime() {
        return testRibbonHystrixService.getFromRibbonHystrix();
    }

    @GetMapping("/test_hystrix")
    public String testHystrix() {
        // 用随机数来模拟错误, 这里让正确率高一些
        return new CommandThatFailsFast(new Random().nextInt(100) < 95).execute();
    }


    @GetMapping("/get_all_instance")
    public List<String> getAllInstance(@RequestParam("service_id") String serviceId) {

        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        return instances.stream()
                .map(instance -> String.format("http://%s:%s", instance.getHost(), instance.getPort()))
                .collect(Collectors.toList());
    }

    @Value("${myName:hing}")
    private String myName;

    @GetMapping("/my_name")
    public String getMyName() {
        return myName;
    }


}
