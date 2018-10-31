package cn.webfuse.demos.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * 在 @Signature 中，
 *      type设置拦截的接口，可选值为：Executor.class，ParameterHandler.class，ResultSetHandler.class，StatementHandler.class
 *      method为设置拦截器中的方法名，也就是上面几个接口中的方法
 *      args设置拦截方法的参数类型数组
 */
@Slf4j
@Intercepts(
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
)
public class SimpleInterceptor implements Interceptor {

    /**
     * MyBatis运行时要执行的拦截方法
     *
     * @param invocation 被拦截对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object result = invocation.proceed();

        return result;
    }

    /**
     * 该方法会在创建被拦截接口的时候调用。
     *
     * @param target 拦截的对象
     * @return
     */
    @Override
    public Object plugin(Object target) {
        //Plugin.wrap方法会自动判断拦截器的签名和被拦截器的对象的接口是否匹配。
        //所以基本上如下写法就OK了
        return Plugin.wrap(target, this);
    }


    /**
     * 设置参数，这个参数可以从外面获得到
     *
     * @param properties 配置的属性
     */
    @Override
    public void setProperties(Properties properties) {
    }
}
