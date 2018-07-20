package network.swan.service.uaa;

import network.swan.frame.annotation.IgnoreDuringSpringScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by guanzhenxing on 2017/11/3.
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@ComponentScan(excludeFilters = @ComponentScan.Filter(IgnoreDuringSpringScan.class))
@ComponentScan(basePackages = "network.swan")
@MapperScan(basePackages = {"network.swan.service.uaa.account.**.repository"})
public class UaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }
}
