package cn.webfuse.framework.exception.handler.impl;


import cn.webfuse.framework.exception.handler.RestfulError;
import cn.webfuse.framework.exception.handler.RestfulErrorConverter;
import cn.webfuse.framework.exception.handler.RestfulErrorResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * https://www.cnblogs.com/raichen/p/8371867.html
 * <p>
 * https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ExceptionHandler.html
 */
@RestControllerAdvice
@ConditionalOnProperty(prefix = "webfuse.mvc", name = "restful-exception-handle.enabled", matchIfMissing = true)
public class DefaultRestfulExceptionHandler {

    /**
     * RestfulError错误转换器
     */
    @Autowired
    private RestfulErrorConverter<DefaultRestfulErrorVO> restfulErrorConverter;
    /**
     * RestfulError错误解析器
     */
    @Autowired
    private RestfulErrorResolver defaultRestfulErrorResolver;

    @ExceptionHandler(Exception.class)
    public DefaultRestfulErrorVO handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        //获得RestfulError实例
        RestfulError restfulError = defaultRestfulErrorResolver.resolveError(request, response, ex);
        if (restfulError == null) {
            return null;
        }
        //解析成返回值
        DefaultRestfulErrorVO res = restfulErrorConverter.convert(restfulError);
        return res;
    }


}
