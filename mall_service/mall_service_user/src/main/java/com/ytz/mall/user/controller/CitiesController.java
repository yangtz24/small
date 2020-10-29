package com.ytz.mall.user.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.user.pojo.Cities;
import com.ytz.mall.user.service.CitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@Api(tags = "CitiesController", description = "城市Controller")
@RestController
@RequestMapping("cities")
@CrossOrigin
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    /***
     * Cities分页条件搜索实现
     * @param cities
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Cities>> findPage(@RequestBody(required = false) Cities cities, @PathVariable int page, @PathVariable int size){
        //调用CitiesService实现分页条件查询Cities
        PageInfo<Cities> pageInfo = citiesService.findPage(cities, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Cities分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Cities>> findPage(@PathVariable int page, @PathVariable int size){
        //调用CitiesService实现分页查询Cities
        PageInfo<Cities> pageInfo = citiesService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param cities
     * @return
     */
    @ApiOperation("条件搜索列表")
    @PostMapping(value = "list" )
    public Result<List<Cities>> findList(@RequestBody(required = false) Cities cities){
        //调用CitiesService实现条件查询Cities
        List<Cities> list = citiesService.findList(cities);
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
    public Result<Integer> delete(@PathVariable Long id){
        //调用CitiesService实现根据主键删除
        int delete = citiesService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改Cities数据
     * @param cities
     * @param id
     * @return
     */
    @ApiOperation("修改")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Cities cities, @PathVariable Long id){
        //设置主键值
        cities.setCityId(id);
        //调用CitiesService实现修改Cities
        int update = citiesService.update(cities);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增Cities数据
     * @param cities
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody Cities cities){
        //调用CitiesService实现添加Cities
        int add = citiesService.add(cities);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询Cities数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<Cities> findById(@PathVariable Long id){
        //调用CitiesService实现根据主键查询Cities
        Cities cities = citiesService.findById(id);
        if (ObjectUtil.isNotEmpty(cities)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", cities);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", cities);
    }

    /***
     * 查询Cities全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<List<Cities>> findAll(){
        //调用CitiesService实现查询所有Cities
        List<Cities> all = citiesService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }
}
