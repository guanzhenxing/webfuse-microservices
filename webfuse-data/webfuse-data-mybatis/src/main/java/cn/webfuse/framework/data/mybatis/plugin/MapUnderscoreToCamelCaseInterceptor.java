package cn.webfuse.framework.data.mybatis.plugin;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;

/**
 * MyBatis Map 类型下画线Key 转小写驼峰形式
 * <p>
 * 参考： https://gitee.com/free/Mybatis_Utils/blob/master/CameHumpMap/CameHumpInterceptor.java
 */
@Intercepts(
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
)
public class MapUnderscoreToCamelCaseInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object proceedResult = invocation.proceed();

        //先执行得到结果，再对结果进行处理
        List<Object> list = (List<Object>) proceedResult;
        for (Object object : list) {
            //如果结果是 Map 类型，就对 Map 的 Key 进行转换
            if (object instanceof Map) {
                processMap((Map) object);
            } else {
                break;
            }
        }
        return list;
    }

    private void processMap(Map<String, Object> object) {
        Set<String> keySet = new HashSet<>(object.keySet());
        for (String key : keySet) {
            Object value = object.get(key);
            object.remove(key);
            object.put(underlineToCamelCase(key), value);
        }
    }

    private String underlineToCamelCase(String key) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key.toLowerCase());
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
