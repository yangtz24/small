package com.ytz.mall.user.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.user.pojo.Areas;
import com.ytz.mall.user.service.AreasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangt
 */
@Api(tags = "AreasController", description = "区域Controller")
@RestController
@RequestMapping("areas")
@CrossOrigin
public class AreasController {

    @Autowired
    private AreasService areasService;

    /***
     * Areas分页条件搜索实现
     * @param areas
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Areas>> findPage(@RequestBody(required = false) Areas areas, @PathVariable int page, @PathVariable int size){
        //调用AreasService实现分页条件查询Areas
        PageInfo<Areas> pageInfo = areasService.findPage(areas, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Areas分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Areas>> findPage(@PathVariable int page, @PathVariable int size){
        //调用AreasService实现分页查询Areas
        PageInfo<Areas> pageInfo = areasService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param areas
     * @return
     */
    @ApiOperation("条件搜索列表")
    @PostMapping(value = "list" )
    public Result<List<Areas>> findList(@RequestBody(required = false) Areas areas){
        //调用AreasService实现条件查询Areas
        List<Areas> list = areasService.findList(areas);
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
        //调用AreasService实现根据主键删除
        int delete = areasService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改Areas数据
     * @param areas
     * @param id
     * @return
     */
    @ApiOperation("修改")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Areas areas,@PathVariable Long id){
        //设置主键值
        areas.setAreaId(id);
        //调用AreasService实现修改Areas
        int update = areasService.update(areas);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增Areas数据
     * @param areas
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody Areas areas){
        //调用AreasService实现添加Areas
        int add = areasService.add(areas);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询Areas数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<Areas> findById(@PathVariable Long id){
        //调用AreasService实现根据主键查询Areas
        Areas areas = areasService.findById(id);
        if (ObjectUtil.isNotEmpty(areas)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", areas);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", areas);
    }

    /***
     * 查询Areas全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<List<Areas>> findAll(){
        //调用AreasService实现查询所有Areas
        List<Areas> all = areasService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }
}
