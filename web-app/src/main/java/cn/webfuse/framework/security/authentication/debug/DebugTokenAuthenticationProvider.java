package cn.webfuse.framework.security.authentication.debug;

import cn.webfuse.framework.security.entity.UserAuthenticationToken;
import cn.webfuse.framework.security.entity.uaa.SecurityAuthToken;
import cn.webfuse.framework.security.entity.uaa.SecurityUser;
import cn.webfuse.framework.security.service.SecurityUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Order(10)
public class DebugTokenAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugTokenAuthenticationProvider.class);

    @Autowired
    private SecurityUserService securityUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        LOGGER.debug("Debug token authenticate begin.");

        Assert.notNull(authentication, "authentication cannot be null.");
        DebugAuthenticationToken debugAuthenticationToken = (DebugAuthenticationToken) authentication;

        SecurityAuthToken securityAuthToken = buildUaaAccessToken(debugAuthenticationToken);
        SecurityUser user = buildUaaUserDetails(debugAuthenticationToken);
        user.setSecurityAuthToken(securityAuthToken);
        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(user, "Debug");

        LOGGER.debug("Debug token authenticate end.");

        return userAuthenticationToken;
    }

    /**
     * 获得用户信息
     *
     * @param debugAuthenticationToken
     * @return
     */
    private SecurityUser buildUaaUserDetails(DebugAuthenticationToken debugAuthenticationToken) {
        return securityUserService.loadSecurityUserByAccount(debugAuthenticationToken.getAccount());
    }

    /**
     * 获得认证信息
     *
     * @param debugAuthenticationToken
     * @return
     */
    private SecurityAuthToken buildUaaAccessToken(DebugAuthenticationToken debugAuthenticationToken) {
        SecurityAuthToken securityAuthToken = new SecurityAuthToken();
        securityAuthToken.setAccount(debugAuthenticationToken.getAccount());
        return securityAuthToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == DebugAuthenticationToken.class;
    }
}
