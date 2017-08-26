package swan.demo;

import network.swan.frame.db.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
