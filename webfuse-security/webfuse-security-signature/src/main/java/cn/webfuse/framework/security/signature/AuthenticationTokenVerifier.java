package cn.webfuse.framework.security.signature;

import cn.webfuse.common.kit.mapper.JsonMapper;
import cn.webfuse.framework.security.signature.authentication.AuthenticationExtractorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证Token的校验者。需要完成以下功能：
 * <ul>
 * <li>获取请求Authorization头信息，并且解析</li>
 * <li>通过Provider进行认证处理</li>
 * <li>认证后获取用户信息</li>
 * </ul>
 */
public class AuthenticationTokenVerifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenVerifier.class);

    private final AuthenticationManager authenticationManager;
    private final AuthenticationExtractorManager extractorManager;

    public AuthenticationTokenVerifier(AuthenticationManager authenticationManager,
                                       AuthenticationExtractorManager extractorManager) {
        this.authenticationManager = authenticationManager;
        this.extractorManager = extractorManager;
    }


    public Authentication doTokenAuthentication(HttpServletRequest request) {


        String authorization = request.getHeader("Authorization"); // 获得请求头
        LOGGER.debug("Header Authorization: {}", authorization);

        Authentication successAuthentication = null;
        if (null != authorization) {
            Authentication authentication = extractorManager.extractAuthentication(authorization, request);
            LOGGER.debug("Authorization: {}", authorization);
            if (null != authentication) {
                successAuthentication = authenticationManager.authenticate(authentication);  //认证
                LOGGER.info("Success Authorization: {}", JsonMapper.defaultMapper().toJson(successAuthentication));
            }
        } else {
            throw new AuthenticationTokenException(403, "AUTHENTICATION_TOKEN_ERROR_TEXT", "没有发现Authorization请求头");
        }
        return successAuthentication;

    }

}
