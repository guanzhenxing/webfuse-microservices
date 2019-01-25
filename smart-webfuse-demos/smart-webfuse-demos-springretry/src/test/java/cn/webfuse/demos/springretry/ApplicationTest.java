package cn.webfuse.demos.springretry;

import cn.webfuse.demos.springretry.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    HelloService helloService;

    @Test
    public void minGoodsNumTest() throws Exception {
        helloService.minGoodsNum(-1);
    }

}

