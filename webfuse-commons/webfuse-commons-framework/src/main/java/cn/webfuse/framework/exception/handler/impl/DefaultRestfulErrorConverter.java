package cn.webfuse.framework.exception.handler.impl;

import cn.webfuse.framework.exception.handler.RestfulError;
import cn.webfuse.framework.exception.handler.RestfulErrorConverter;
import org.apache.commons.lang3.StringUtils;

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
public class DefaultRestfulErrorConverter implements RestfulErrorConverter<DefaultRestfulErrorVO> {

    private String codeKey = "code";
    private String messageKey = "message";
    private String serverTimeKey = "server_time";
    private String developerMessageKey = "developer_message";

    @Override
    public DefaultRestfulErrorVO convert(RestfulError restfulError) {
        DefaultRestfulErrorVO vo = new DefaultRestfulErrorVO();
        vo.setCode(restfulError.getCode());
        vo.setServerTime(restfulError.getServerTime());
        vo.setMessage(restfulError.getMessage());
        vo.setDeveloperMessage(restfulError.getDeveloperMessage());
        return vo;
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

