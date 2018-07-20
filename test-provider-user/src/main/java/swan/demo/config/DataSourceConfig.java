package swan.demo.config;


import network.swan.frame.db.datasource.DynamicDataSourceRegister;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 数据源配置
 */
@Configuration
@ComponentScan(basePackages = {"network.swan.frame.db.datasource"})
@Import(DynamicDataSourceRegister.class)
public class DataSourceConfig {
}
