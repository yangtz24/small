package com.ytz.mall.oauth.controller;

import cn.hutool.core.util.ObjectUtil;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.oauth.service.LoginService;
import com.ytz.mall.oauth.util.AuthToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangt
 */
@Api(tags = "UserLoginController", description = "登录Controller")
@RestController
@RequestMapping("user/oauth")
public class UserLoginController {

    @Autowired
    private LoginService loginService;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    /**
     * 授权模式 密码模式
     */
    private static final String GRAND_TYPE = "password";


    /**
     * 密码模式  认证.
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("登录认证")
    @GetMapping("login")
    public Result<AuthToken> login(String username, String password) {
        //登录 之后生成令牌的数据返回
        AuthToken authToken = loginService.login(username, password, clientId, clientSecret, GRAND_TYPE);

        if (ObjectUtil.isNotEmpty(authToken)) {
            return new Result<>(true, StatusCode.SUCCESS,"令牌生成成功",authToken);
        }
        return new Result<>(false, StatusCode.ERROR,"令牌生成失败",authToken);

    }


}
