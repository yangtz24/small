package com.ytz.mall.user.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.user.pojo.Provinces;
import com.ytz.mall.user.service.ProvincesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangt
 */
@Api(tags = "ProvincesController", description = "省份Controller")
@RestController
@RequestMapping("provinces")
@CrossOrigin
public class ProvincesController {

    @Autowired
    private ProvincesService provincesService;

    /***
     * Provinces分页条件搜索实现
     * @param provinces
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Provinces>> findPage(@RequestBody(required = false) Provinces provinces, @PathVariable  int page, @PathVariable  int size){
        //调用ProvincesService实现分页条件查询Provinces
        PageInfo<Provinces> pageInfo = provincesService.findPage(provinces, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Provinces分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //调用ProvincesService实现分页查询Provinces
        PageInfo<Provinces> pageInfo = provincesService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param provinces
     * @return
     */
    @ApiOperation("条件搜索")
    @PostMapping(value = "list" )
    public Result<List<Provinces>> findList(@RequestBody(required = false) Provinces provinces){
        //调用ProvincesService实现条件查询Provinces
        List<Provinces> list = provincesService.findList(provinces);
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
        //调用ProvincesService实现根据主键删除
        int delete = provincesService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改Provinces数据
     * @param provinces
     * @param id
     * @return
     */
    @ApiOperation("修改")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Provinces provinces,@PathVariable Long id){
        //设置主键值
        provinces.setProvinceId(id);
        //调用ProvincesService实现修改Provinces
        int update = provincesService.update(provinces);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增Provinces数据
     * @param provinces
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody Provinces provinces){
        //调用ProvincesService实现添加Provinces
        int add = provincesService.add(provinces);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询Provinces数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<Provinces> findById(@PathVariable Long id){
        //调用ProvincesService实现根据主键查询Provinces
        Provinces provinces = provincesService.findById(id);
        if (ObjectUtil.isNotEmpty(provinces)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", provinces);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", provinces);
    }

    /***
     * 查询Provinces全部数据
     * @return
     */
    @ApiOperation("分页条件搜索")
    @GetMapping
    public Result<List<Provinces>> findAll(){
        //调用ProvincesService实现查询所有Provinces
        List<Provinces> all = provincesService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }
}
