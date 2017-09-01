package swan.demo.dao;


import network.swan.frame.db.datasource.TargetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import swan.demo.jdbc.SQLExecutor;
import swan.demo.model.Customer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guanzhenxing on 2017/8/9.
 */
@Repository
public class CustomerRepository {

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @Autowired
    private CustomerMapper customerMapper;

    @TargetDataSource(value = "default")
    public List<Customer> findAll() {

        try {
//            new SQLExecutor(dataSource).query("", Customer.class);
            System.out.println(new SQLExecutor(dataSource).getDataSource().getConnection().getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();
    }

    @TargetDataSource(value = "ds1")
    public void addCustomer(String name, String email) {

        try {
            System.out.println(new SQLExecutor(dataSource).getDataSource().getConnection().getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @TargetDataSource(value = "ds2")
    public void doAll() {
        try {
            System.out.println(new SQLExecutor(dataSource).getDataSource().getConnection().getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Autowired
//    MultiSqlSessionFactory multiSqlSessionFactory;
//
//    public void testMyBatis() {
//        SqlSessionFactory toFactory = multiSqlSessionFactory.get("");
//
//        SqlSession sqlSession = null;
//        try {
//            sqlSession = toFactory.openSession(ExecutorType.BATCH, false);
////            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//            sqlSession.clearCache();
//        } catch (Exception e) {
//            if (sqlSession != null)
//                sqlSession.rollback();
//
//        } finally {
//            if (sqlSession != null)
//                sqlSession.close();
//        }
//
//
//    }

}
