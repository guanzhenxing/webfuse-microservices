package cn.webfuse.framework.security.authentication.bearer;

import cn.webfuse.framework.security.authentication.AuthenticationInterceptor;
import cn.webfuse.framework.security.authentication.AuthenticationInterceptorImpl;
import cn.webfuse.framework.security.entity.UserAuthenticationToken;
import cn.webfuse.framework.security.entity.uaa.SecurityAuthToken;
import cn.webfuse.framework.security.entity.uaa.SecurityUser;
import cn.webfuse.framework.security.service.impl.BearerAuthenticationTokenCheckService;
import cn.webfuse.framework.security.service.SecurityUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * BearerToken认证
 */
@Component
@Order(10)
public class BearerTokenAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerTokenAuthenticationProvider.class);

    private AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptorImpl();

    @Autowired
    private SecurityUserService securityUserService;


    /**
     * 引入定制的AuthenticationInterceptor，且名字为bearerAuthenticationInterceptor
     *
     * @param authenticationInterceptor
     */
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired(required = false)
    @Qualifier(value = "bearerAuthenticationInterceptor")
    public void setAuthenticationInterceptor(AuthenticationInterceptor authenticationInterceptor) {
        if (authenticationInterceptor != null) {
            this.authenticationInterceptor = authenticationInterceptor;
        }
    }

    @Autowired
    private BearerAuthenticationTokenCheckService bearerAuthenticationTokenCheckService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        LOGGER.debug("Bearer token authenticate begin.");
        authenticationInterceptor.preHandle(authentication);

        BearerAuthenticationToken bearerPreAuthenticationToken = (BearerAuthenticationToken) authentication;
        SecurityAuthToken securityAuthToken = bearerAuthenticationTokenCheckService.verifyToken(bearerPreAuthenticationToken);
        SecurityUser user = securityUserService.loadUserDetailsByAccessToken(securityAuthToken.getAccessToken());    //获得相关的用户信息
        user.setSecurityAuthToken(securityAuthToken);
        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(user, "BEARER");

        authenticationInterceptor.postHandle(userAuthenticationToken);
        LOGGER.debug("Bearer token authenticate end.");

        return userAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == BearerAuthenticationToken.class;
    }
}

