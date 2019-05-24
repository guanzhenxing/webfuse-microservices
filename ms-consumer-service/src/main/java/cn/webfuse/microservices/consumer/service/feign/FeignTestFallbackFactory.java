package cn.webfuse.microservices.consumer.service.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignTestFallbackFactory implements FallbackFactory<FeignTestClient> {


    @Override
    public FeignTestClient create(Throwable cause) {
        return new FeignTestClient() {
            @Override
            public String getTime() {
                return "now";
            }
        };
    }
}
