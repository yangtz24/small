package com.ytz.mall.oauth.util;

import java.io.Serializable;

/****
 * @author yangt
 * @Description:用户令牌封装
 *****/
public class AuthToken implements Serializable{

    private static final long serialVersionUID = 906497487124626024L;
    /**
     * 令牌信息
     */
    String accessToken;
    /**
     * 刷新tSUCCESSen(refresh_tSUCCESSen)
     */
    String refreshToken;
    /**
     * 刷新tSUCCESSen(refresh_tSUCCESSen)
     */
    String jti;

    public String getaccessToken() {
        return accessToken;
    }

    public void setaccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getrefreshToken() {
        return refreshToken;
    }

    public void setrefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }
}