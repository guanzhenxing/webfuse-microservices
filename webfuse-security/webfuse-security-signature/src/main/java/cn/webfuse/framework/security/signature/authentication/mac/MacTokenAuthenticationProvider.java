package cn.webfuse.framework.security.signature.authentication.mac;

import cn.webfuse.framework.security.signature.authentication.AuthenticationInterceptor;
import cn.webfuse.framework.security.signature.authentication.AuthenticationInterceptorImpl;
import cn.webfuse.framework.security.signature.entity.SecurityUser;
import cn.webfuse.framework.security.signature.entity.UserAuthenticationToken;
import cn.webfuse.framework.security.signature.entity.SecurityToken;
import cn.webfuse.framework.security.signature.service.SecurityUserService;
import cn.webfuse.framework.security.signature.service.impl.MacAuthenticationTokenCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class MacTokenAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MacTokenAuthenticationProvider.class);

    private AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptorImpl();

    @Autowired
    private MacAuthenticationTokenCheckService macAuthenticationTokenCheckService;

    @Autowired
    private SecurityUserService securityUserService;

    /**
     * 引入定制的AuthenticationInterceptor，且名字为macAuthenticationInterceptor
     *
     * @param authenticationInterceptor
     */
    @Autowired(required = false)
    @Qualifier(value = "macAuthenticationInterceptor")
    public void setAuthenticationInterceptor(AuthenticationInterceptor authenticationInterceptor) {
        if (authenticationInterceptor != null) {
            this.authenticationInterceptor = authenticationInterceptor;
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        LOGGER.debug("Mac signature authenticate begin.");
        authenticationInterceptor.preHandle(authentication);

        MacAuthenticationToken macAuthenticationToken = (MacAuthenticationToken) authentication;
        SecurityToken securityToken = macAuthenticationTokenCheckService.verifyToken(macAuthenticationToken); //校验token
        SecurityUser user = securityUserService.loadUserByAccessToken(securityToken.getAccessToken());    //获得相关的用户信息
        user.setSecurityToken(securityToken);
        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(user, "MAC");

        authenticationInterceptor.postHandle(userAuthenticationToken);
        LOGGER.debug("Mac signature authenticate end.");

        return userAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == MacAuthenticationToken.class;
    }
}
