package cn.webfuse.framework.exception.handler.impl;

import java.util.Date;

/**
 * RestfulError的返回信息
 */
public class DefaultRestfulErrorVO {

    /**
     * 错误的代码
     */
    private String code;
    /**
     * 错误的信息
     */
    private String message;
    /**
     * 开发者信息
     */
    private String developerMessage;

    /**
     * 发生错误的服务器时间
     */
    private Date serverTime;

    /**
     * 请求的ID(如果有)
     */
    private String requestId;

    /**
     * 服务器实例ID(如果有)
     */
    private String hostId;
    /**
     * 错误解决的文档(如果有)
     */
    private String document;

    public DefaultRestfulErrorVO() {

    }

    public DefaultRestfulErrorVO(String code, String message, String developerMessage,
                                 Date serverTime, String requestId, String hostId,
                                 String document) {
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
        this.serverTime = serverTime;
        this.requestId = requestId;
        this.hostId = hostId;
        this.document = document;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
