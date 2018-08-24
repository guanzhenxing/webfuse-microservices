package cn.webfuse.framework.security.authentication.bearer;

import cn.webfuse.framework.security.authentication.AbstractAuthenticationExtractor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * BearerToken认证提取器
 */
@Component
@Order(10)
public class BearerTokenAuthenticationExtractor extends AbstractAuthenticationExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerTokenAuthenticationExtractor.class);

    @Override
    public String getPrefix() {
        return "BEARER";
    }

    @Override
    public Authentication extractAuthentication(String authenticationValue, HttpServletRequest request) throws AuthenticationException {

        String bearerToken = StringUtils.strip(authenticationValue, "\"");
        Validate.notBlank(bearerToken, "bearer token is missing.");
        LOGGER.debug("bearerToken:{}", bearerToken);

        return new BearerAuthenticationToken(bearerToken);
    }
}