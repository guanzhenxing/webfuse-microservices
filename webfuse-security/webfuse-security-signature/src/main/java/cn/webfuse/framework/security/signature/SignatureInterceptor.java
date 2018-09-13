package cn.webfuse.framework.security.signature;


import cn.webfuse.common.kit.mapper.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限拦截器
 */
public class SignatureInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignatureInterceptor.class);

    @Autowired
    private AuthenticationTokenVerifier authenticationTokenVerifier;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        SignatureVerification signatureVerification = method.getAnnotation(SignatureVerification.class);
        if (signatureVerification == null) { //如果不需要校验，则立马返回
            return true;
        }
        Authentication authentication = authenticationTokenVerifier.doTokenAuthentication(request);

        LOGGER.debug("after signature verify ,the authentication is : {}", JsonMapper.defaultMapper().toJson(authentication));

        //TODO 拦截权限

        return false;
    }


}
