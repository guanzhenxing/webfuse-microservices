package cn.webfuse.framework.security.signature.service.impl;

import cn.webfuse.common.kit.mapper.JsonMapper;
import cn.webfuse.framework.security.signature.AuthenticationTokenException;
import cn.webfuse.framework.security.signature.authentication.bearer.BearerAuthenticationToken;
import cn.webfuse.framework.security.signature.entity.uaa.SecurityAuthToken;
import cn.webfuse.framework.security.signature.service.AuthenticationTokenCheckService;
import cn.webfuse.framework.security.signature.service.SecurityUserService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class BearerAuthenticationTokenCheckService implements AuthenticationTokenCheckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthenticationTokenCheckService.class);

    @Autowired
    private SecurityUserService securityUserService;

    @Override
    public SecurityAuthToken verifyToken(Authentication wafAuthenticationToken) {
        LOGGER.debug("verify bearer signature begin.");

        Validate.notNull(wafAuthenticationToken, "authenticationToken must not be null");
        BearerAuthenticationToken bearerPreAuthenticationToken = (BearerAuthenticationToken) wafAuthenticationToken;
        SecurityAuthToken securityAuthToken = checkAuthToken(bearerPreAuthenticationToken.getToken());

        LOGGER.debug("verify bearer signature end.");
        return securityAuthToken;
    }

    private SecurityAuthToken checkAuthToken(String token) {
        SecurityAuthToken securityAuthToken = getAuthToken(token);
        if (securityAuthToken == null) {
            throw new AuthenticationTokenException(403, "AUTH_TOKEN_EXPIRED", "The signature does not exist or has expired");
        }
        if (securityAuthToken.isExpire()) {
            throw new AuthenticationTokenException(403, "AUTH_TOKEN_EXPIRED", "The signature has expired");
        }

        LOGGER.debug(JsonMapper.defaultMapper().toJson(securityAuthToken));

        return securityAuthToken;
    }

    private SecurityAuthToken getAuthToken(String token) {
        return securityUserService.loadSecurityAuthTokenByAccessToken(token);
    }
}
