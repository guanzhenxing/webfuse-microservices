package cn.webfuse.framework.data.mybatis.plugin.pageable;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Mybatis - 通用分页拦截器
 *
 * @author liuzh
 * @version 1.0.1
 * <p>
 * 在原来的代码上做了一些重构修改
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Intercepts(
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class,
                        RowBounds.class, ResultHandler.class}
        )
)
public class PageInterceptor implements Interceptor {
    private static final List<ResultMapping> EMPTY_RESULT_MAPPING = new ArrayList<>(0);
    private Dialect dialect;
    private Field additionalParametersField;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取拦截方法的参数
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameterObject = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];

        boolean skipPaging = dialect.skipPaging(mappedStatement.getId(), parameterObject, rowBounds);

        //调用方法判断是否需要进行分页，如果不需要，直接返回结果
        if (false == skipPaging) {
            //当前的目标对象
            Executor executor = (Executor) invocation.getTarget();
            BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
            //反射获取动态参数
            Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField.get(boundSql);

            boolean willDoCount = dialect.willDoCount(mappedStatement.getId(), parameterObject, rowBounds);
            if (willDoCount) {
                Long count = getCountNumber(mappedStatement, parameterObject, rowBounds, resultHandler, executor, boundSql, additionalParameters);
                //处理查询总数
                dialect.afterCount(count, parameterObject, rowBounds);
                if (count == 0L) {
                    //当查询总数为 0 时，直接返回空的结果
                    return dialect.afterPaging(new ArrayList(), parameterObject, rowBounds);
                }
            }

            boolean willDoPaging = dialect.willDoPaging(mappedStatement.getId(), parameterObject, rowBounds);
            if (willDoPaging) {
                List resultList = getResultList(mappedStatement, parameterObject, rowBounds, resultHandler, executor, boundSql, additionalParameters);
                return dialect.afterPaging(resultList, parameterObject, rowBounds);
            }
        }
        //返回默认查询
        return invocation.proceed();
    }


    private Long getCountNumber(MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds,
                                ResultHandler resultHandler, Executor executor, BoundSql boundSql,
                                Map<String, Object> additionalParameters) throws java.sql.SQLException {

        //根据当前的 MappedStatement 创建一个返回值为 Long 类型的 MappedStatement
        MappedStatement countMappedStatement = newMappedStatement(mappedStatement, Long.class);
        //创建 count 查询的缓存 key
        CacheKey countKey = executor.createCacheKey(countMappedStatement, parameterObject, RowBounds.DEFAULT, boundSql);
        //调用方言获取 count sql
        String countSql = dialect.getCountSql(boundSql, parameterObject, rowBounds, countKey);
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
        //当使用动态 SQL 时，可能会产生临时的参数，这些参数需要手动设置到新的 BoundSql 中
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        //执行 count 查询
        Object countResultList = executor.query(countMappedStatement, parameterObject, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        return (Long) ((List) countResultList).get(0);

    }

    private List getResultList(MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds,
                               ResultHandler resultHandler, Executor executor, BoundSql boundSql,
                               Map<String, Object> additionalParameters) throws java.sql.SQLException {

        //生成分页的缓存 key
        CacheKey pageKey = executor.createCacheKey(mappedStatement, parameterObject, rowBounds, boundSql);
        //调用方言获取分页 sql
        String pageSql = dialect.getPageSql(boundSql, parameterObject, rowBounds, pageKey);
        BoundSql pageBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameterObject);
        //设置动态参数
        for (String key : additionalParameters.keySet()) {
            pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        //执行分页查询
        return executor.query(mappedStatement, parameterObject, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundSql);
    }

    /**
     * 根据现有的 ms 创建一个新的，使用新的返回值类型
     *
     * @param mappedStatement
     * @param resultType
     * @return
     */
    public MappedStatement newMappedStatement(MappedStatement mappedStatement, Class<?> resultType) {
        MappedStatement.Builder builder = new MappedStatement.Builder(
                mappedStatement.getConfiguration(), mappedStatement.getId() + "_Count",
                mappedStatement.getSqlSource(), mappedStatement.getSqlCommandType()
        );
        builder.resource(mappedStatement.getResource());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.statementType(mappedStatement.getStatementType());
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        if (mappedStatement.getKeyProperties() != null && mappedStatement.getKeyProperties().length != 0) {
            String keyProperties = String.join(",", mappedStatement.getKeyProperties());
            builder.keyProperty(keyProperties);
        }
        builder.timeout(mappedStatement.getTimeout());
        builder.parameterMap(mappedStatement.getParameterMap());
        //count查询返回值int
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap resultMap = new ResultMap.Builder(
                mappedStatement.getConfiguration(), mappedStatement.getId(),
                resultType, EMPTY_RESULT_MAPPING).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(mappedStatement.getResultSetType());
        builder.cache(mappedStatement.getCache());
        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
        builder.useCache(mappedStatement.isUseCache());
        return builder.build();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialectClass = properties.getProperty("dialect");
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("使用 PageInterceptor 分页插件时，必须设置 dialect 属性");
        }
        dialect.setProperties(properties);
        try {
            //反射获取 BoundSql 中的 additionalParameters 属性
            additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
            additionalParametersField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}

