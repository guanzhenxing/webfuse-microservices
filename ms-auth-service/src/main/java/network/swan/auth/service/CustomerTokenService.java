package network.swan.auth.service;

import network.swan.auth.models.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * Created by guanzhenxing on 2017/9/3.
 */
public class CustomerTokenService extends DefaultTokenServices {

    Logger logger = LoggerFactory.getLogger(CustomerTokenService.class);


    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        return super.readAccessToken(accessToken);
    }


    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken token = super.createAccessToken(authentication);
        Account account = (Account) authentication.getPrincipal();
        String jti = (String) token.getAdditionalInformation().get("jti");

        return token;
    }

    @Override
    public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest) throws AuthenticationException {
        logger.info("refresh token:" + refreshTokenValue);
        String jti = tokenRequest.getRequestParameters().get("jti");
        OAuth2AccessToken token = super.refreshAccessToken(refreshTokenValue, tokenRequest);
        return token;
    }
}
