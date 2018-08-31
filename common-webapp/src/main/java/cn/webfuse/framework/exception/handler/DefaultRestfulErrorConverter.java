package cn.webfuse.framework.exception.handler;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的RestfulError转换器，默认转换为Map。也就是在这里输出异常的格式。<br/>
 * 默认显示：<br/>
 * code: 错误的代码<br/>
 * message: 错误的信息<br/>
 * server_time: 发生错误的服务器时间<br/>
 * developer_message：开发者信息<br/>
 * request_id: 请求的ID(如果有)<br/>
 * host_id: 服务器实例ID(如果有)<br/>
 * document: 错误解决的文档(如果有)<br/>
 */
public class DefaultRestfulErrorConverter implements RestfulErrorConverter<Map> {

    private String codeKey = "code";
    private String messageKey = "message";
    private String serverTimeKey = "server_time";
    private String developerMessageKey = "developer_message";

    @Override
    public Map convert(RestfulError restfulError) {

        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put(getCodeKey(), restfulError.getCode());
        map.put(getServerTimeKey(), restfulError.getServerTime());

        if (!StringUtils.isEmpty(restfulError.getMessage())) {
            map.put(getMessageKey(), restfulError.getMessage());
        }
        if (!StringUtils.isEmpty(developerMessageKey)) {
            map.put(getDeveloperMessageKey(), restfulError.getDeveloperMessage());
        }
        return map;
    }


    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getServerTimeKey() {
        return serverTimeKey;
    }

    public void setServerTimeKey(String serverTimeKey) {
        this.serverTimeKey = serverTimeKey;
    }


    public String getDeveloperMessageKey() {
        return developerMessageKey;
    }

    public void setDeveloperMessageKey(String developerMessageKey) {
        this.developerMessageKey = developerMessageKey;
    }
}

