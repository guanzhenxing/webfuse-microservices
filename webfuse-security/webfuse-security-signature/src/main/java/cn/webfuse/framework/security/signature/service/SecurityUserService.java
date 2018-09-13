package cn.webfuse.framework.security.signature.service;

import cn.webfuse.framework.security.signature.entity.uaa.SecurityAuthToken;
import cn.webfuse.framework.security.signature.entity.uaa.SecurityUser;

/**
 * 从Uaa中获得数据的服务接口
 */
public interface SecurityUserService {


    /**
     * 根据用户账号获得用户信息
     *
     * @param account
     * @return
     */
    SecurityUser loadSecurityUserByAccount(String account);

    /**
     * 根据accessToken获得用户信息
     *
     * @param accessToken
     * @return
     */
    SecurityUser loadSecurityUserByAccessToken(String accessToken);

    /**
     * 根据accessToken获得Token信息
     *
     * @param accessToken
     * @return
     */
    SecurityAuthToken loadSecurityAuthTokenByAccessToken(String accessToken);


}
