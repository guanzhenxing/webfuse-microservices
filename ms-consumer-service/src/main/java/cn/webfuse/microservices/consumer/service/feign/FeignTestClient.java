package cn.webfuse.microservices.consumer.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ms-producer-service", fallback = FeignTestFallbackFactory.class)
public interface FeignTestClient {

    @GetMapping("get_time")
    String getTime();
}
