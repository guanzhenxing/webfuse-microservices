package cn.webfuse.framework.security.authentication;

import com.google.common.base.Splitter;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 认证提取器的抽象类。
 */
public abstract class AbstractAuthenticationExtractor implements AuthenticationExtractor {

    /**
     * 将认证数据映射到一个Map中
     *
     * @param data
     * @return
     */
    protected Map<String, String> splitToMap(String data) {
        return new CaseInsensitiveMap(Splitter.on(",").trimResults().withKeyValueSeparator(Splitter.on("=").trimResults().limit(2)).split(data));
    }

    /**
     * 获得认证信的某个值
     *
     * @param dataMap
     * @param name
     * @return
     */
    protected String getValue(Map<String, String> dataMap, String name) {
        String value = dataMap.get(name);
        if (value != null) {
            value = StringUtils.strip(value, "\"");
        }
        return value;
    }

}
