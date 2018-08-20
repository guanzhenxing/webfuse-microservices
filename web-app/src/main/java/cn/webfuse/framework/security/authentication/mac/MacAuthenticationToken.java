package cn.webfuse.framework.security.authentication.mac;

import cn.webfuse.framework.security.authentication.AbstractCustomAuthenticationToken;

public class MacAuthenticationToken extends AbstractCustomAuthenticationToken {

    private String id;
    private String mac;
    private String nonce;
    private String httpMethod;
    private String requestUri;
    private String host;

    public MacAuthenticationToken(String id, String mac, String nonce, String httpMethod, String requestUri, String host) {
        this.id = id;
        this.mac = mac;
        this.nonce = nonce;
        this.httpMethod = httpMethod;
        this.requestUri = requestUri;
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
