package cn.webfuse.framework.security.authentication.mac;

import cn.webfuse.framework.security.authentication.AuthenticationInterceptor;
import cn.webfuse.framework.security.authentication.AuthenticationInterceptorImpl;
import cn.webfuse.framework.security.entity.UserAuthenticationToken;
import cn.webfuse.framework.security.entity.uaa.SecurityAuthToken;
import cn.webfuse.framework.security.entity.uaa.SecurityUser;
import cn.webfuse.framework.security.service.SecurityUserService;
import cn.webfuse.framework.security.service.impl.MacAuthenticationTokenCheckService;
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

        LOGGER.debug("Mac token authenticate begin.");
        authenticationInterceptor.preHandle(authentication);

        MacAuthenticationToken macAuthenticationToken = (MacAuthenticationToken) authentication;
        SecurityAuthToken securityAuthToken = macAuthenticationTokenCheckService.verifyToken(macAuthenticationToken); //校验token
        SecurityUser user = securityUserService.loadUserDetailsByAccessToken(securityAuthToken.getAccessToken());    //获得相关的用户信息
        user.setSecurityAuthToken(securityAuthToken);
        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(user, "MAC");

        authenticationInterceptor.postHandle(userAuthenticationToken);
        LOGGER.debug("Mac token authenticate end.");

        return userAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == MacAuthenticationToken.class;
    }
}
