package cn.webfuse.framework.ratelimit;

import cn.webfuse.framework.web.interceptor.BaseInterceptor;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public abstract class RateLimiterInterceptor extends BaseInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        doIpRateLimit(request);
        doAllRateLimit(request);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);

        if (rateLimiter != null) {
            doMethodRateLimit(request, method);
        }

        return true;
    }

    /**
     * IP 限流
     *
     * @param request
     */
    protected abstract void doIpRateLimit(HttpServletRequest request) throws Exception;

    /**
     * 全部方法限流
     *
     * @param request
     */
    protected abstract void doAllRateLimit(HttpServletRequest request);

    /**
     * 对某个方法限流¬
     * @param request
     * @param method
     */
    protected abstract void doMethodRateLimit(HttpServletRequest request, Method method);


}
