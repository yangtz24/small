package com.ytz.mall.order.controller;

import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.order.config.TokenDecode;
import com.ytz.mall.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


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
    @RequestMapping("add")
    public Result add(Long id, Integer num) {
        //springsecurity 获取当前的用户名 传递service

        Map userInfo = tokenDecode.getUserInfo();
        String username = (String) userInfo.get("username");

        System.out.println("哇塞::用户名:"+username);

        cartService.add(id, num, username);
        return new Result(true, StatusCode.SUCCESS, "添加成功");

    }

    @RequestMapping("list")
    public Result<Map<Object, Object>> list() {
        Map userInfo = tokenDecode.getUserInfo();
        String username = (String) userInfo.get("username");
        System.out.println("哇塞::用户名:"+username);
        Map<Object, Object> map = cartService.list(username);
        return new Result<>(true, StatusCode.SUCCESS, "列表查询成功", map);


    }


}
