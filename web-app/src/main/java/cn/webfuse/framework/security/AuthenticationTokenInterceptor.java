package cn.webfuse.framework.security;


import cn.webfuse.framework.web.interceptor.BaseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Token认证拦截器
 */
public class AuthenticationTokenInterceptor extends BaseInterceptor {

    @Autowired
    private AuthenticationTokenVerifier authenticationTokenVerifier;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        SecurityVerification securityVerification = method.getAnnotation(SecurityVerification.class);
        if (securityVerification == null) { //如果不需要校验，则立马返回
            return true;
        }
        Authentication authentication = authenticationTokenVerifier.doTokenAuthentication();
        return false;
    }


}
