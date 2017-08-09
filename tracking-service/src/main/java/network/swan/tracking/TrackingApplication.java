package network.swan.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * Created by guanzhenxing on 2017/8/9.
 */
@SpringBootApplication
@EnableZipkinServer
public class TrackingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrackingApplication.class, args);
    }
}
