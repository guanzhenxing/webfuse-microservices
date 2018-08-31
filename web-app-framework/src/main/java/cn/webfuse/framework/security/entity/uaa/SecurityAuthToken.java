package cn.webfuse.framework.security.entity.uaa;

import java.util.Date;

/**
 * UAA的认证信息
 */
public class SecurityAuthToken {

    /**
     * 认证后的用户账号
     */
    private String account;

    /**
     * 验证后的token
     */
    private String accessToken;

    /**
     * 过期刷新后的token
     */
    private String refreshToken;

    /**
     * 过期时间
     */
    private Date expiresAt;
    /**
     * 服务器返回时间
     */
    private Date serverTime;
    /**
     * hmac 的密钥
     */
    private String secret;

    /**
     * hmac加密算法
     */
    private String algorithm;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * 判断是否过期
     */
    public boolean isExpire() {
        Date start = new Date();
        Date end = getExpiresAt();
        if (end == null) {
            end = new Date();
        }
        return (end.getTime() - start.getTime()) < 0L;
    }
}
