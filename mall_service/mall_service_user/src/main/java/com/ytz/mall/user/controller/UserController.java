package com.ytz.mall.user.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.BCrypt;
import com.ytz.mall.common.JwtUtil;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.user.pojo.User;
import com.ytz.mall.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author yangt
 */
@Api(tags = "UserController", description = "用户Controller")
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}")
    public Result<PageInfo<User>> findPage(@RequestBody(required = false) User user, @PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页条件查询User
        PageInfo<User> pageInfo = userService.findPage(user, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}")
    public Result<PageInfo<User>> findPage(@PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页查询User
        PageInfo<User> pageInfo = userService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @ApiOperation("条件搜索")
    @PostMapping(value = "list")
    public Result<List<User>> findList(@RequestBody(required = false) User user) {
        //调用UserService实现条件查询User
        List<User> list = userService.findList(user);
        if (CollUtil.isNotEmpty(list)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", list);
        }
        return new Result<>(false, StatusCode.ERROR, "查询失败", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation("删除")
    @DeleteMapping(value = "{id}")
    public Result<Integer> delete(@PathVariable Long id) {
        //调用UserService实现根据主键删除
        int delete = userService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @ApiOperation("修改")
    @PutMapping(value = "{id}")
    public Result<Integer> update(@RequestBody User user, @PathVariable Long id) {
        //设置主键值
        user.setUserId(id);
        //调用UserService实现修改User
        int update = userService.update(user);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody User user) {
        //调用UserService实现添加User
        int add = userService.add(user);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<User> findById(@PathVariable Long id) {
        //调用UserService实现根据主键查询User
        User user = userService.findById(id);
        if (ObjectUtil.isNotEmpty(user)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", user);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", user);
    }

    /***
     * 查询User全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<List<User>> findAll() {
        //调用UserService实现查询所有User
        List<User> all = userService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param response
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public Result<User> login(String username, String password, HttpServletResponse response) {
        //1.从数据库中查询用户名对应的用户的对象
        User user = userService.findByName(username);
        if (ObjectUtil.isEmpty(user)) {
            //2.判断用户是否为空 为空返回数据
            return new Result<>(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
        //3如果不为空格 判断 密码是否正确 正确则登录成功

        if(BCrypt.checkpw(password,user.getPassword())){
            //成功
            Map<String,Object> info = new HashMap<String,Object>(16);
            info.put("role","USER");
            info.put("success","SUCCESS");
            info.put("userName",username);

            //1.生成令牌
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
            //2.设置cookie中
            Cookie cookie = new Cookie("Authorization",token);
            response.addCookie(cookie);
            //3.设置头文件中
            response.setHeader("Authorization",token);

            return new Result<>(true, StatusCode.SUCCESS, "成功", token);
        }else{
            //失败
            return new Result<>(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
    }
}
