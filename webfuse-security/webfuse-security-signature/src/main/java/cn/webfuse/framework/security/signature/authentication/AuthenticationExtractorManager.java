package cn.webfuse.framework.security.signature.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证信息提取器管理。<br/>
 * 在此类中会根据prefix信息来调用相应到提取类，提取认证信息。
 */
public class AuthenticationExtractorManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationExtractorManager.class);

    //解析器列表
    private final ArrayList<AuthenticationExtractor> extractors = new ArrayList<>();

    /**
     * 添加提取器
     *
     * @param extractor
     */
    public void append(AuthenticationExtractor extractor) {
        extractors.add(extractor);
    }

    /**
     * 添加提取器
     *
     * @param extractors
     */
    @Autowired
    public void append(List<AuthenticationExtractor> extractors) {
        this.extractors.addAll(extractors);
    }

    /**
     * 提取认证信息
     *
     * @param authentication
     * @param request
     * @return
     */
    public Authentication extractAuthentication(String authentication, HttpServletRequest request) throws AuthenticationException {

        Assert.notNull(authentication, "authentication cannot be null.");

        int spaceIndex = authentication.indexOf(" ");
        if (spaceIndex > -1) {
            String prefix = authentication.substring(0, spaceIndex);
            String value = authentication.substring(spaceIndex).trim();

            LOGGER.debug("Extract authentication, prefix:{}, value:{}", prefix, value);

            for (AuthenticationExtractor extractor : extractors) {
                if (extractor.getPrefix().equalsIgnoreCase(prefix)) {
                    return extractor.extractAuthentication(value, request); //提取权限信息
                }
            }
        }
        return null;
    }


}