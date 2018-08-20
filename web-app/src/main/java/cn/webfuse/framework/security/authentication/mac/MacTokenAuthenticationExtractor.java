package cn.webfuse.framework.security.authentication.mac;

import cn.webfuse.framework.security.authentication.AbstractAuthenticationExtractor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Order(10)
public class MacTokenAuthenticationExtractor extends AbstractAuthenticationExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MacTokenAuthenticationExtractor.class);

    @Override
    public String getPrefix() {
        return "MAC";
    }

    @Override
    public Authentication extractAuthentication(String authenticationValue, HttpServletRequest request) throws AuthenticationException {

        Validate.notBlank(authenticationValue, "Mac token should contains text.");

        String host = request.getHeader(HttpHeaders.HOST);

        Validate.notBlank(host, "'host' must not be null or empty");

        String requestURI = this.getRequestURI(request, host);
        LOGGER.debug("requestURI:{}, host:{}", requestURI, host);

        Map<String, String> authMap = splitToMap(authenticationValue);  //分解，得到各个值

        String id = getValue(authMap, "id");
        String nonce = getValue(authMap, "nonce");
        String mac = getValue(authMap, "mac");

        Validate.notBlank(id, "'id' must not be null or empty. Token is " + authenticationValue);
        Validate.notBlank(nonce, "'nonce' must not be null or empty. Token is " + authenticationValue);
        Validate.notBlank(mac, "'mac' must not be null or empty. Token is " + authenticationValue);

        LOGGER.debug("mac:{}, id:{}, nonce:{}", mac, id, nonce);

        return new MacAuthenticationToken(id, mac, nonce, request.getMethod(), requestURI, host);
    }

    /**
     * 获得请求的URI
     *
     * @param request
     * @return
     */
    private String getRequestURI(HttpServletRequest request, String host) {
        String reqString = request.getRequestURL().toString();
        String queryStr = request.getQueryString();
        // 判断请求参数是否为空
        if (!StringUtils.isEmpty(queryStr)) {
            reqString = reqString + "?" + queryStr;
        }

        int index = reqString.indexOf(host);
        if (index == -1) {
            return reqString;
        }

        return reqString.substring(index + host.length());
    }
}
