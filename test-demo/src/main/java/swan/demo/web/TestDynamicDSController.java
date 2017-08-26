package swan.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import swan.demo.service.TestDynamicDSService;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@RestController
public class TestDynamicDSController {

    @Autowired
    TestDynamicDSService testDynamicDSService;

    @GetMapping("/default")
    public Properties testDefault() throws SQLException {
        DataSource dataSource = testDynamicDSService.testDefaultDataSource();
        return dataSource.getConnection().getClientInfo();
    }

    @GetMapping("/ds1")
    public DataSource testDS1() throws SQLException {
        DataSource dataSource = testDynamicDSService.testDs1();
        return dataSource;
    }

    @GetMapping("/ds2")
    public DataSource testDS2() throws SQLException {
        DataSource dataSource = testDynamicDSService.testDs2();
        return dataSource;
    }


}
