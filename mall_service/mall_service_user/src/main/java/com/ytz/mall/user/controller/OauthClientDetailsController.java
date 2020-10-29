package com.ytz.mall.user.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.user.pojo.OauthClientDetails;
import com.ytz.mall.user.service.OauthClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author yangt
 */
@Api(tags = "OauthClientDetailsController", description = "认证Controller")
@RestController
@RequestMapping("oauthClientDetails")
@CrossOrigin
public class OauthClientDetailsController {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    /***
     * OauthClientDetails分页条件搜索实现
     * @param oauthClientDetails
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<OauthClientDetails>> findPage(@RequestBody(required = false) OauthClientDetails oauthClientDetails, @PathVariable int page, @PathVariable int size){
        //调用OauthClientDetailsService实现分页条件查询OauthClientDetails
        PageInfo<OauthClientDetails> pageInfo = oauthClientDetailsService.findPage(oauthClientDetails, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * OauthClientDetails分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //调用OauthClientDetailsService实现分页查询OauthClientDetails
        PageInfo<OauthClientDetails> pageInfo = oauthClientDetailsService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param oauthClientDetails
     * @return
     */
    @ApiOperation("条件搜索")
    @PostMapping(value = "list" )
    public Result<List<OauthClientDetails>> findList(@RequestBody(required = false) OauthClientDetails oauthClientDetails){
        //调用OauthClientDetailsService实现条件查询OauthClientDetails
        List<OauthClientDetails> list = oauthClientDetailsService.findList(oauthClientDetails);
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
    @DeleteMapping(value = "{id}" )
    public Result<Integer> delete(@PathVariable String id){
        //调用OauthClientDetailsService实现根据主键删除
        int delete = oauthClientDetailsService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改OauthClientDetails数据
     * @param oauthClientDetails
     * @param id
     * @return
     */
    @ApiOperation("修改")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody OauthClientDetails oauthClientDetails,@PathVariable String id){
        //设置主键值
        oauthClientDetails.setClientId(id);
        //调用OauthClientDetailsService实现修改OauthClientDetails
        int update = oauthClientDetailsService.update(oauthClientDetails);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增OauthClientDetails数据
     * @param oauthClientDetails
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody OauthClientDetails oauthClientDetails){
        //调用OauthClientDetailsService实现添加OauthClientDetails
        int add = oauthClientDetailsService.add(oauthClientDetails);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询OauthClientDetails数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<OauthClientDetails> findById(@PathVariable String id){
        //调用OauthClientDetailsService实现根据主键查询OauthClientDetails
        OauthClientDetails oauthClientDetails = oauthClientDetailsService.findById(id);
        if (ObjectUtil.isNotEmpty(oauthClientDetails)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", oauthClientDetails);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", oauthClientDetails);
    }

    /***
     * 查询OauthClientDetails全部数据
     * @return
     */
    @ApiOperation("分页条件搜索")
    @GetMapping
    public Result<List<OauthClientDetails>> findAll(){
        //调用OauthClientDetailsService实现查询所有OauthClientDetails
        List<OauthClientDetails> all = oauthClientDetailsService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }
}
