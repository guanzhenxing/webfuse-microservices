package cn.webfuse.framework.security.signature.authentication.bearer;

import cn.webfuse.framework.security.signature.authentication.AbstractCustomAuthenticationToken;

/**
 * Bearer认证token
 */
public class BearerAuthenticationToken extends AbstractCustomAuthenticationToken {

    private String token;

    public BearerAuthenticationToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
