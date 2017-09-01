package swan.demo;

import network.swan.frame.db.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class HikaricpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HikaricpDemoApplication.class, args);
    }

}
