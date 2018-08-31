package cn.webfuse.framework.security.authentication;

import org.springframework.security.core.Authentication;

/**
 * 认证处理拦截器
 */
public interface AuthenticationInterceptor {

    /**
     * 认证前调用
     *
     * @param authentication 认证信息
     */
    void preHandle(Authentication authentication);

    /**
     * 认证成功后调用
     *
     * @param authentication 认证信息
     */
    void postHandle(Authentication authentication);


}