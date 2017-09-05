package network.swan.frame.db;

import network.swan.frame.db.datasource.DynamicDataSourceRegister;
import org.springframework.context.annotation.Import;

@Import(DynamicDataSourceRegister.class)
public abstract class AbstractDBConfig {
}
