package cn.webfuse.framework.security.signature.service;


import cn.webfuse.framework.security.signature.entity.SecurityToken;
import cn.webfuse.framework.security.signature.entity.SecurityUser;

public interface SecurityUserService {

    SecurityUser loadUserByAccount(String account);

    SecurityUser loadUserByAccessToken(String accessToken);

    SecurityToken loadSecurityTokenByAccessToken(String token);
}
