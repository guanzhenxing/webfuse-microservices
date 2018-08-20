package cn.webfuse.framework.security.service.impl;

import cn.webfuse.framework.security.entity.uaa.SecurityAuthToken;
import cn.webfuse.framework.security.entity.uaa.SecurityUser;
import cn.webfuse.framework.security.service.SecurityUserService;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserServiceImpl implements SecurityUserService {
    @Override
    public SecurityUser loadUserDetailsByAccount(String account) {
        return null;
    }

    @Override
    public SecurityUser loadUserDetailsByAccessToken(SecurityAuthToken securityAuthToken) {
        return null;
    }

    @Override
    public SecurityAuthToken loadUaaAccessToken(String accessToken) {
        return null;
    }
}
