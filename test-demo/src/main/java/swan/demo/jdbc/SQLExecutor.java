package swan.demo.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.lang.invoke.MethodHandles;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guanzhenxing on 2017/8/9.
 */
public class SQLExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SQLExecutor(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        setExceptionTranslator();
    }

    /**
     * 定义异常处理机制
     */
    private void setExceptionTranslator() {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) getJdbcOperations();
        DefaultSQLExceptionTranslator dst = new DefaultSQLExceptionTranslator();
        jdbcTemplate.setExceptionTranslator(dst);
    }

    /**
     * 返回JdbcOperations的实现，即JdbcTemplate
     *
     * @return JdbcOperations对象
     */
    public JdbcOperations getJdbcOperations() {
        return namedParameterJdbcTemplate.getJdbcOperations();
    }

    /**
     * 获得数据源
     *
     * @return 数据源对象
     */
    public DataSource getDataSource() {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) getJdbcOperations();
        // 获得默认的dataSourde
        return jdbcTemplate.getDataSource();
    }

    /**
     * 返回NamedParameterJdbcTemplate对象
     *
     * @return NamedParameterJdbcTemplate对象
     */
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    /**
     * 执行不带参数的批量操作
     *
     * @param sql 更新SQL语句集
     * @return
     */
    public int[] batchUpdate(String[] sql) {
        return getJdbcOperations().batchUpdate(sql);
    }

    /**
     * 执行带参数的批量操作<br/>
     * <p>
     * 这里的SQL不支持命名参数，只能用?代替
     *
     * @param sql       更新SQL语句
     * @param batchArgs 参数列表
     * @return 受影响的条数数组
     */
    public int[] batchUpdate(String sql, List<Object[]> batchArgs) {
        return getJdbcOperations().batchUpdate(sql, batchArgs);
    }

    /**
     * 执行带命名参数的批量操作<br/>
     * <p>
     * 这里的SQL支持命名参数
     *
     * @param sql         更新SQL语句
     * @param batchValues 参数列表
     * @return 受影响的条数数组
     */
    public int[] batchUpdate(String sql, Map<String, ?>[] batchValues) {
        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues);
    }

    /**
     * 执行不带参数的更新语句
     *
     * @param sql 更新SQL语句
     * @return 受影响的条数
     */
    public int update(String sql) {
        return getJdbcOperations().update(sql);
    }

    /**
     * 执行只有一个参数的情况下的更新语句
     *
     * @param sql        更新SQL语句
     * @param namedParam 命名参数
     * @param value      命名参数值
     * @return 受影响的行数
     */
    public int update(String sql, String namedParam, Object value) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(namedParam, value);
        return update(sql, paramMap);
    }

    /**
     * 执行带命名参数的更新语句
     *
     * @param sql      更新SQL语句
     * @param paramMap 命名参数
     * @return 受影响的行数
     */
    public int update(String sql, Map<String, ?> paramMap) {
        return namedParameterJdbcTemplate.update(sql, paramMap);
    }

    /**
     * 执行不带参数的查询并返回指定类型的集合
     *
     * @param sql          查询SQL语句
     * @param requiredType 对象类型
     * @return 查询的结果对象集
     */
    public <T> List<T> query(String sql, Class<T> requiredType) {
        return query(sql, new HashMap<>(), requiredType);
    }

    /**
     * 执行带命名参数的查询并返回指定类型的集合
     *
     * @param sql          查询SQL语句
     * @param paramMap     命名参数
     * @param requiredType 对象类型
     * @return 查询的结果对象集
     */
    public <T> List<T> query(String sql, Map<String, ?> paramMap,
                             Class<T> requiredType) {
        RowMapper<T> rm = new BeanPropertyRowMapper<>(requiredType);
        return namedParameterJdbcTemplate.query(sql, paramMap, rm);

    }

    /**
     * 执行不带参数的查询并返回List<br/>
     * <p>
     * list中包含map，每个map对应一条记录。map中的key为列名，value为值
     *
     * @param sql 查询SQL语句
     * @return 每一行对应一个Map的List
     */
    public List<Map<String, Object>> query(String sql) {
        return query(sql, new HashMap<>());
    }

    /**
     * 执行带命名参数的查询并返回List<br/>
     * <p>
     * list中包含map，每个map对应一条记录。map中的key为列名，value为值
     *
     * @param sql      查询SQL语句
     * @param paramMap 命名参数
     * @return 每一行对应一个Map的List
     */
    public List<Map<String, Object>> query(String sql, Map<String, ?> paramMap) {

        final List<Map<String, Object>> resultList = new ArrayList<>();
        mapResult(sql, paramMap, resultList);
        return resultList;
    }

    /**
     * 这是一个不算完整的重构后方法，主要作用是进行查询以后再做映射操作
     *
     * @param sql
     * @param paramMap
     * @param resultList
     */
    private void mapResult(String sql, Map<String, ?> paramMap, List<Map<String, Object>> resultList) {
        namedParameterJdbcTemplate.query(sql, paramMap,
                rs -> {
                    Map<String, Object> map = new HashMap<>();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String columnLabel = rsmd.getColumnLabel(i);
                        Object obj = rs.getObject(i);
                        map.put(columnLabel, obj);
                    }
                    resultList.add(map);
                });
    }

    /**
     * 执行不带参数的查询并返回单个类型对象<br/>
     * <p>
     * 该方法的查询只能返回一条记录<br/>
     * <p>
     * ps:如果查询的结果中有多条记录，那么返回的是第一条记录
     *
     * @param sql          查询SQL语句
     * @param requiredType 指定的类型
     * @return 查询到的类型对象
     */
    public <T> T queryForObject(String sql, Class<T> requiredType) {
        return queryForObject(sql, new HashMap<>(), requiredType);
    }

    /**
     * 执行带命名参数的查询并返回指定类型的对象<br/>
     * <p>
     * 该方法的查询只能返回一条记录<br/>
     * <p>
     * ps:如果查询的结果中有多条记录，那么返回的是第一条记录
     *
     * @param sql          查询SQL语句
     * @param paramMap     命名参数
     * @param requiredType 指定的类型
     * @return 查询到的类型对象
     */
    public <T> T queryForObject(String sql, Map<String, ?> paramMap,
                                Class<T> requiredType) {

        Package classPackage = requiredType.getPackage();
        String packName = classPackage.getName();
        T obj;
        try {
            // 如果requiredType的类属于java.lang或者java.util包，那么不需要进行转换
            if (packName.contains("java.lang")
                    || packName.contains("java.util")) {
                obj = namedParameterJdbcTemplate.queryForObject(sql, paramMap,
                        requiredType);
            } else {
                RowMapper<T> rm = new BeanPropertyRowMapper<>(requiredType);
                obj = namedParameterJdbcTemplate.queryForObject(sql, paramMap,
                        rm);
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSizeDataAccessException e) {
            List<T> list = query(sql, requiredType);
            return list.get(0);
        }
        return obj;
    }

    /**
     * 执行不带参数的查询并返回Map<br/>
     * <p>
     * 该方法的查询只能返回一条记录
     *
     * @param sql 查询语句
     * @return 每一行对应一个Map
     */
    public Map<String, Object> queryForMap(String sql) {
        return queryForMap(sql, new HashMap<>());
    }

    /**
     * 执行带命名参数的查询并返回Map<br/>
     * <p>
     * 该方法的查询只能返回一条记录<br/>
     * <p>
     * ps:如果查询的结果中有多条记录，那么返回的是第一条记录
     *
     * @param sql      查询语句
     * @param paramMap 命名参数
     * @return 每一行对应的一个Map
     */
    public Map<String, Object> queryForMap(String sql, Map<String, ?> paramMap) {
        // 当没有查询到数值的时候，spring底层没有处理，需要自己处理
        try {
            return namedParameterJdbcTemplate.queryForMap(sql, paramMap);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSizeDataAccessException e) {
            List<Map<String, Object>> list = query(sql, paramMap);
            return list.get(0);
        }
    }

    /**
     * 执行不带参数的查询并返回List<br/>
     * <p>
     * list中包含map，每个map对应一条记录。map中的key为列名，value为值
     *
     * @param sql 查询语句
     * @return 每一行对应一个Map的List
     */
    public List<Map<String, Object>> queryForList(String sql) {
        Map<String, ?> paramMap = new HashMap<>();
        return queryForList(sql, paramMap);
    }

    /**
     * 执行带命名参数的查询并返回List<br/>
     * <p>
     * list中包含map，每个map对应一条记录。map中的key为列名，value为值
     *
     * @param sql      查询语句
     * @param paramMap 命名参数
     * @return 每一行对应一个Map的List
     */
    public List<Map<String, Object>> queryForList(String sql,
                                                  Map<String, ?> paramMap) {
        final List<Map<String, Object>> resultList = new ArrayList<>();
        mapResult(sql, paramMap, resultList);
        return resultList;
    }

    /**
     * 执行不带参数的查询并返回指定类型的对象集
     *
     * @param sql          查询语句
     * @param requiredType 指定的类型
     * @return 查询的结果对象集
     */
    public <T> List<T> queryForList(String sql, Class<T> requiredType) {
        return query(sql, requiredType);
    }

    /**
     * 执行带参数的查询并返回指定类型的对象集
     *
     * @param sql          查询语句
     * @param paramMap     命名参数
     * @param requiredType 指定的类型
     * @return 查询的结果对象集
     */
    public <T> List<T> queryForList(String sql, Map<String, ?> paramMap,
                                    Class<T> requiredType) {
        return query(sql, paramMap, requiredType);
    }

}
