package swan.demo.service;

import network.swan.frame.db.datasource.TargetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;

@Service
public class TestDynamicDSService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    DataSource dataSource;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @TargetDataSource("default")
    public DataSource testDefaultDataSource() throws SQLException {
        namedParameterJdbcTemplate.getJdbcOperations();
        return dataSource;
    }

    @TargetDataSource("ds1")
    public DataSource testDs1() {
        namedParameterJdbcTemplate.getJdbcOperations();
        return dataSource;
    }

    @TargetDataSource("ds2")
    public DataSource testDs2() {
        namedParameterJdbcTemplate.getJdbcOperations();
        return dataSource;
    }

}
