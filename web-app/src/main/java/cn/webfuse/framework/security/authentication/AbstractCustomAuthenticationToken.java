package cn.webfuse.framework.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * 抽象的认证信息，用于封装认证信息
 */
public abstract class AbstractCustomAuthenticationToken extends AbstractAuthenticationToken {

    public AbstractCustomAuthenticationToken() {
        super(null);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
