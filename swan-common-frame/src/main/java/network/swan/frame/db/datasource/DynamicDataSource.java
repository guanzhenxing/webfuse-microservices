package network.swan.frame.db.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * Created by guanzhenxing on 2017/8/9.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceName();
        return dataSourceName;
    }
}
