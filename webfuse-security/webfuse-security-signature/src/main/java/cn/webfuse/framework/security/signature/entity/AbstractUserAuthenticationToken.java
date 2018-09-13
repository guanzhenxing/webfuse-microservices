package cn.webfuse.framework.security.signature.entity;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public abstract class AbstractUserAuthenticationToken extends AbstractAuthenticationToken {

    private final String authType;

    /**
     * Creates a signature with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public AbstractUserAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String authType) {
        super(authorities);
        this.authType = authType;
    }

    public String getAuthType() {
        return authType;
    }
}