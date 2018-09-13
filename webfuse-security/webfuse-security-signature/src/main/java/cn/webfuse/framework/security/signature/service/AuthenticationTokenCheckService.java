package cn.webfuse.framework.security.signature.service;

import cn.webfuse.framework.security.signature.entity.uaa.SecurityAuthToken;
import org.springframework.security.core.Authentication;

/**
 * Uaa验证token的逻辑
 */
public interface AuthenticationTokenCheckService {
    /**
     * 校验Token
     *
     * @param wafAuthenticationToken
     * @return
     */
    SecurityAuthToken verifyToken(Authentication wafAuthenticationToken);
}
