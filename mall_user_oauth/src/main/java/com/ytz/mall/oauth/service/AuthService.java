package com.ytz.mall.oauth.service;


import com.ytz.mall.oauth.util.AuthToken;

/**
 * @author yangt
 */
public interface AuthService {

    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
