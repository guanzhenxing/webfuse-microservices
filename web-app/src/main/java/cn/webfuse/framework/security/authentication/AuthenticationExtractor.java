package cn.webfuse.framework.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequest请求头Authorization信息的解析器。<br>
 */
public interface AuthenticationExtractor {
    /**
     * 获得头部前缀信息：Mac,Bearer或者Debug
     *
     * @return
     */
    String getPrefix();

    /**
     * 提取认证信息
     *
     * @param value
     * @param request
     * @return
     */
    Authentication extractAuthentication(String value, HttpServletRequest request) throws AuthenticationException;
}
