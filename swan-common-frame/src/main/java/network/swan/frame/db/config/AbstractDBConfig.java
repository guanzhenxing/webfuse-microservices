package network.swan.frame.db.config;

import network.swan.frame.db.datasource.DynamicDataSourceRegister;
import org.springframework.context.annotation.Import;

/**
 * 数据库配置的抽象类。<br/>
 * 在系统中只要继承此类，即可默认配置数据库
 */
@Import(DynamicDataSourceRegister.class)
public abstract class AbstractDBConfig {
}
