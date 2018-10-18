package cn.webfuse.framework.security.signature.entity;


public class UserAuthenticationToken extends AbstractUserAuthenticationToken {

    private SecurityUser user;

    public UserAuthenticationToken(SecurityUser user, String authType) {
        super(user.getAuthorities(), authType);
        setAuthenticated(true);
        this.user = user;
    }


    @Override
    public Object getCredentials() {
        return this.user.getSecurityToken();
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

}
