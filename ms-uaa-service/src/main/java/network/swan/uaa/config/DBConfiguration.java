package network.swan.uaa.config;

import network.swan.frame.db.datasource.DynamicDataSourceRegister;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 数据源配置
 */
@Configuration
@Import(DynamicDataSourceRegister.class)
@ComponentScan(basePackages = {"network.swan.frame.db"})
public class DBConfiguration {


}
