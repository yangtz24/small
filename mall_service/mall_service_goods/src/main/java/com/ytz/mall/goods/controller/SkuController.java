package com.ytz.mall.goods.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Sku;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.goods.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SkuController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/18
 * @Version: V1.0
 */
@Api(tags = "SkuController", description = "SkuController")
@RestController
@RequestMapping("sku")
@CrossOrigin
public class SkuController {

    @Autowired
    private SkuService skuService;

    /***
     * Sku分页条件搜索实现
     * @param sku
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}")
    public Result<PageInfo<Sku>> findPage(@RequestBody(required = false) Sku sku, @PathVariable int page, @PathVariable int size){
        //调用SkuService实现分页条件查询Sku
        PageInfo<Sku> pageInfo = skuService.findPage(sku, page, size);
        if (ObjectUtil.isNotNull(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Sku分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}")
    public Result<PageInfo<Sku>> findPage(@PathVariable int page, @PathVariable int size){
        //调用SkuService实现分页查询Sku
        PageInfo<Sku> pageInfo = skuService.findPage(page, size);
        if (ObjectUtil.isNotNull(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param sku
     * @return
     */
    @ApiOperation("条件搜索")
    @PostMapping(value = "list")
    public Result<List<Sku>> findList(@RequestBody(required = false) Sku sku){
        //调用SkuService实现条件查询Sku
        List<Sku> list = skuService.findList(sku);
        if (CollUtil.isNotEmpty(list)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", list);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation("删除数据")
    @DeleteMapping(value = "{id}")
    public Result<Integer> delete(@PathVariable Long id){
        //调用SkuService实现根据主键删除
        int delete = skuService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功", delete);
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败", delete);
    }

    /***
     * 修改Sku数据
     * @param sku
     * @param id
     * @return
     */
    @ApiOperation("修改数据")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Sku sku,@PathVariable Long id){
        //设置主键值
        sku.setId(id);
        //调用SkuService实现修改Sku
        int update = skuService.update(sku);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功", update);
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败", update);
    }

    /***
     * 新增Sku数据
     * @param sku
     * @return
     */
    @ApiOperation("添加数据")
    @PostMapping
    public Result<Integer> add(@RequestBody Sku sku){
        //调用SkuService实现添加Sku
        int add = skuService.add(sku);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功", add);
        }
        return new Result<>(false, StatusCode.ERROR,"添加失败", add);
    }

    /***
     * 根据ID查询Sku数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<Sku> findById(@PathVariable Long id){
        //调用SkuService实现根据主键查询Sku
        Sku sku = skuService.findById(id);
        if (ObjectUtil.isNotNull(sku)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",sku);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",sku);
    }

    /***
     * 查询Sku全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<List<Sku>> findAll(){
        //调用SkuService实现查询所有Sku
        List<Sku> all = skuService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", all);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",all);
    }

    /***
     * 查询Sku部分数据
     * @return
     */
    @ApiOperation("查询部分")
    @GetMapping("limit")
    public Result<List<Sku>> findLimitAll(){
        //调用SkuService实现查询所有Sku
        List<Sku> limits = skuService.findlimitAll();
        if (CollUtil.isNotEmpty(limits)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", limits);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",limits);
    }
}

