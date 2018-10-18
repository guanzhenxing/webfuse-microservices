package cn.webfuse.framework.security.signature.service;

import cn.webfuse.framework.security.signature.entity.SecurityToken;
import org.springframework.security.core.Authentication;

public interface AuthenticationTokenCheckService {
    /**
     * 校验Token
     *
     * @param wafAuthenticationToken
     * @return
     */
    SecurityToken verifyToken(Authentication wafAuthenticationToken);
}
