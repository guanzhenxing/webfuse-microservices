package cn.webfuse.framework.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.invoke.MethodHandles;

/**
 * 基础的拦截器
 */
public abstract class AbstractBaseInterceptor implements HandlerInterceptor {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


}
