package cn.webfuse.demos.springretry.controller;

import cn.webfuse.demos.springretry.service.HelloService;
import org.apache.commons.collections.map.SingletonMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    public HelloService helloService;

    @GetMapping("/test_retry")
    public Map<String, Object> testRetry(@RequestParam int num) throws Exception {
        int res = helloService.minGoodsNum(num);
        return new SingletonMap("res", res);
    }


}
