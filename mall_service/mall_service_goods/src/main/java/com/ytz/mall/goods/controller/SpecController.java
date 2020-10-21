package com.ytz.mall.goods.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Spec;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.goods.service.SpecService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SpecController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@Api(tags = "SpecController", description = "品牌Controller")
@RestController
@RequestMapping("spec")
@CrossOrigin
public class SpecController {

    @Autowired
    private SpecService specService;

    /***
     * Spec分页条件搜索实现
     * @param spec
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Spec>> findPage(@RequestBody(required = false) Spec spec, @PathVariable int page, @PathVariable int size){
        //执行搜索
        PageInfo<Spec> pageInfo = specService.findPage(spec, page, size);
        if (ObjectUtil.isNotNull(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);

    }

    /***
     * Spec分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Spec>> findPage(@PathVariable int page, @PathVariable int size){
        //分页查询
        PageInfo<Spec> pageInfo = specService.findPage(page, size);
        if (ObjectUtil.isNotNull(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param spec
     * @return
     */
    @ApiOperation("多条件搜索数据")
    @PostMapping(value = "list" )
    public Result<List<Spec>> findList(@RequestBody(required = false)  Spec spec){
        List<Spec> list = specService.findList(spec);
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
    @DeleteMapping(value = "{id}" )
    public Result<Integer> delete(@PathVariable Long id){
        int delete = specService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功", delete);
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败", delete);
    }

    /***
     * 修改Spec数据
     * @param spec
     * @param id
     * @return
     */
    @ApiOperation("修改数据")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Spec spec, @PathVariable Long id){
        //设置主键值
        spec.setId(id);
        //修改数据
        int update = specService.update(spec);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功", update);
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败", update);
    }

    /***
     * 新增Spec数据
     * @param spec
     * @return
     */
    @ApiOperation("添加数据")
    @PostMapping
    public Result<Integer> add(@RequestBody Spec spec){
        int add = specService.add(spec);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功", add);
        }
        return new Result<>(false, StatusCode.ERROR,"添加失败", add);
    }

    /***
     * 根据ID查询Spec数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个数据")
    @GetMapping("{id}")
    public Result<Spec> findById(@PathVariable Long id){
        //根据ID查询
        Spec spec = specService.findById(id);
        if (ObjectUtil.isNotNull(spec)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",spec);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",spec);
    }

    /***
     * 查询Spec全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<Spec> findAll(){
        List<Spec> all = specService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", all);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",all);
    }

    /***
     * 根据分类ID查询对应的规格列表
     */
    @ApiOperation("根据分类ID查询")
    @GetMapping(value = "category/{id}")
    public Result<List<Spec>> findByCategoryId(@PathVariable(value = "id")Long categoryId){
        //调用Service查询
        List<Spec> specs = specService.findByCategoryId(categoryId);
        if (CollUtil.isNotEmpty(specs)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", specs);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",specs);
    }
}
