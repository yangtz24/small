package com.ytz.mall.order.controller;

import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.order.config.TokenDecode;
import com.ytz.mall.order.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author yangt
 */
@Api(tags = "CartController", description = "购物车Controller")
@RestController
@RequestMapping("cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;



    @Autowired
    private TokenDecode tokenDecode;

    /**
     * 添加购物车
     *
     * @param id  要购买的商品的SKU的ID
     * @param num 要购买的数量
     * @return
     */
    @ApiOperation("添加购物车")
    @PostMapping("{id}/{num}")
    public Result add(@PathVariable("id") Long id, @PathVariable("num") Integer num) {
        //springsecurity 获取当前的用户名 传递service

        Map userInfo = tokenDecode.getUserInfo();
        String username = (String) userInfo.get("username");

        cartService.add(id, num, username);
        return new Result(true, StatusCode.SUCCESS, "添加成功");

    }

    /**
     * 查询购物车列表
     * @return
     */
    @ApiOperation("查询购物车列表")
    @GetMapping
    public Result<Map<Object, Object>> list() {
        Map userInfo = tokenDecode.getUserInfo();
        String username = (String) userInfo.get("username");
        Map<Object, Object> map = cartService.list(username);
        return new Result<>(true, StatusCode.SUCCESS, "列表查询成功", map);


    }


}
