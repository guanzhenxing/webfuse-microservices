package cn.webfuse.framework.security.signature.authentication.debug;


import cn.webfuse.framework.security.signature.entity.SecurityToken;
import cn.webfuse.framework.security.signature.entity.SecurityUser;
import cn.webfuse.framework.security.signature.entity.UserAuthenticationToken;
import cn.webfuse.framework.security.signature.service.SecurityUserService;
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

        LOGGER.debug("Debug signature authenticate begin.");

        Assert.notNull(authentication, "authentication cannot be null.");
        DebugAuthenticationToken debugAuthenticationToken = (DebugAuthenticationToken) authentication;

        SecurityToken securityToken = buildAuthenticationToken(debugAuthenticationToken);
        SecurityUser user = buildUser(debugAuthenticationToken);
        user.setSecurityToken(securityToken);
        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(user, "Debug");

        LOGGER.debug("Debug signature authenticate end.");

        return userAuthenticationToken;
    }

    /**
     * 获得用户信息
     *
     * @param debugAuthenticationToken
     * @return
     */
    private SecurityUser buildUser(DebugAuthenticationToken debugAuthenticationToken) {
        return securityUserService.loadUserByAccount(debugAuthenticationToken.getAccount());
    }

    /**
     * 获得认证信息
     *
     * @param debugAuthenticationToken
     * @return
     */
    private SecurityToken buildAuthenticationToken(DebugAuthenticationToken debugAuthenticationToken) {
        SecurityToken securityToken = new SecurityToken();
        securityToken.setAccount(debugAuthenticationToken.getAccount());
        return securityToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == DebugAuthenticationToken.class;
    }
}
