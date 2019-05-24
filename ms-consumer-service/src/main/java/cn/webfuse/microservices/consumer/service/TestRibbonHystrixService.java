package cn.webfuse.microservices.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestRibbonHystrixService {

    @Autowired
    @Qualifier("loadBalanced")
    private RestTemplate loadBalanced;

    @HystrixCommand(fallbackMethod = "fallback")
    public String getFromRibbonHystrix() {
        return loadBalanced.getForEntity("http://ms-producer-service/get_time", String.class).getBody();
    }

    /**
     * 发生错误，执行fallback。实现 服务降级
     * @return
     */
    public String fallback() {
        return "fallback";
    }


}
