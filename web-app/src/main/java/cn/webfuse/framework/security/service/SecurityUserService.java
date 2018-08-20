package cn.webfuse.framework.security.service;

import cn.webfuse.framework.security.entity.uaa.SecurityAuthToken;
import cn.webfuse.framework.security.entity.uaa.SecurityUser;

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
    SecurityUser loadUserDetailsByAccount(String account);

    /**
     * 根据UaaAuthToken获得用户信息
     *
     * @param securityAuthToken
     * @return
     */
    SecurityUser loadUserDetailsByAccessToken(SecurityAuthToken securityAuthToken);

    /**
     * 根据accessToken获得Token信息
     *
     * @param accessToken
     * @return
     */
    SecurityAuthToken loadUaaAccessToken(String accessToken);


}
