package cn.webfuse.microservices.consumer.service;

import cn.webfuse.microservices.consumer.service.feign.FeignTestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestFeignHystrixService {

    @Autowired
    FeignTestClient feignClient;

    public String getFromFeignHystrix() {
        return feignClient.getTime();
    }
}
