package network.swan.ms;
/**
 * Created by guanzhenxing on 2017/8/5.
 */

import network.swan.ms.annotation.IgnoreDuringScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@EnableFeignClients
@ComponentScan(excludeFilters = @ComponentScan.Filter(IgnoreDuringScan.class))
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}