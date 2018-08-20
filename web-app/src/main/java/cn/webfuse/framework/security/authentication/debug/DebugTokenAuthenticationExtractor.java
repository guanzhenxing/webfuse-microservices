package cn.webfuse.framework.security.authentication.debug;

import cn.webfuse.framework.exception.AuthenticationTokenException;
import cn.webfuse.framework.support.GlobalContext;
import cn.webfuse.framework.security.authentication.AbstractAuthenticationExtractor;
import org.apache.commons.lang3.Validate;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Order(10)
public class DebugTokenAuthenticationExtractor extends AbstractAuthenticationExtractor {
    @Override
    public String getPrefix() {
        return "DEBUG";
    }


    @Override
    public Authentication extractAuthentication(String authenticationValue, HttpServletRequest request) throws AuthenticationException {

        if (!GlobalContext.isDebugMode()) {
            throw new AuthenticationTokenException(403, "FORBIDDEN_DEBUG", "当前非调试模式，无法使用。");
        }
        Validate.notBlank(authenticationValue, "Debug token should contains text.");

        Map<String, String> authMap = splitToMap(authenticationValue);  //分解，得到各个值

        String account = getValue(authMap, "account");
        Validate.notBlank(account, "Debug token property userId is missing.");

        String realm = getValue(authMap, "realm");
        //TODO 对realm进行解析

        return new DebugAuthenticationToken(account, realm);
    }
}