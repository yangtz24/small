package com.ytz.mall.oauth.controller;

import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.oauth.service.AuthService;
import com.ytz.mall.oauth.util.AuthToken;
import com.ytz.mall.oauth.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;


/**
 * @Description: TODO
 * @Param: 
 * @Return: 
 * @Author: yangt
 * @Date: 2020/10/29
**/

@RestController
@RequestMapping(value = "oauth")
public class AuthController {

    /**
     * 客户端ID
     */
    @Value("${auth.clientId}")
    private String clientId;

    /**
     * 秘钥
     */
    @Value("${auth.clientSecret}")
    private String clientSecret;

    /**
     * cookie存储的域名
     */
    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    /**
     * cookie生命周期
     */
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @Autowired
    AuthService authService;

    @PostMapping("login")
    public Result<String> login(String username, String password) {
        if(StringUtils.isEmpty(username)){
            throw new RuntimeException("用户名不允许为空");
        }
        if(StringUtils.isEmpty(password)){
            throw new RuntimeException("密码不允许为空");
        }
        //申请令牌
        AuthToken authToken =  authService.login(username,password,clientId,clientSecret);

        //用户身份令牌
        String accessToken = authToken.getaccessToken();

        //将令牌存储到cookie
        savecookie(accessToken);

        return new Result<>(true, StatusCode.SUCCESS,"登录成功！");
    }

    /***
     * 将令牌存储到cookie
     * @param token
     */
    private void savecookie(String token){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.addcookie(response,cookieDomain,"/","Authorization",token,cookieMaxAge,false);
    }
}
