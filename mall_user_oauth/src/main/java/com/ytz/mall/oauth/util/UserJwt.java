package com.ytz.mall.oauth.util;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


/**
 * @author yangt
 */
@Setter
@Getter
public class UserJwt extends User {
    private static final long serialVersionUID = -1910441479383785633L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名字
     */
    private String name;

    /**
     * 设置公司
     */
    private String company;

    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


}
