package network.swan.frame.db.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import java.sql.SQLException;

/**
 * SQL异常转换处理类
 * Created by guanzhenxing on 2017/8/9.
 */
public class JdbcSQLExceptionTranslator implements SQLExceptionTranslator {


    private static Logger log = LoggerFactory.getLogger(JdbcSQLExceptionTranslator.class);

    @Override
    public DataAccessException translate(String task, String sql, SQLException ex) {
        if (task == null) {
            task = "";
        }
        if (sql == null) {
            sql = "";
        }

        log.error("执行SQL语句：" + sql + " 错误！异常如下：", ex);
        return new UncategorizedSQLException(task, sql, ex);
    }


    protected String buildMessage(String task, String sql, SQLException ex) {
        return task + "; SQL [" + sql + "]; " + ex.getMessage();
    }

}
