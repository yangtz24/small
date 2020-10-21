package com.ytz.mall.goods.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Category;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.goods.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: CategoryController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@Api(tags = "CategoryController", description = "商品分类Controller")
@RestController
@RequestMapping("category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /***
     * Category分页条件搜索实现
     * @param category
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Category>> findPage(@RequestBody(required = false) Category category, @PathVariable int page, @PathVariable  int size){
        //执行搜索
        PageInfo<Category> pageInfo = categoryService.findPage(category, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Category分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //分页查询
        PageInfo<Category> pageInfo = categoryService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param category
     * @return
     */
    @ApiOperation("多条件搜索")
    @PostMapping(value = "list" )
    public Result<List<Category>> findList(@RequestBody(required = false) Category category){
        List<Category> list = categoryService.findList(category);
        if (CollUtil.isNotEmpty(list)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",list);
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
        int delete = categoryService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功", delete);
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败", delete);
    }

    /***
     * 修改Category数据
     * @param category
     * @param id
     * @return
     */
    @ApiOperation("修改分类")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Category category,@PathVariable Long id){
        //设置主键值
        category.setId(id);
        //修改数据
        int update = categoryService.update(category);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功", update);
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败", update);

    }

    /***
     * 新增Category数据
     * @param category
     * @return
     */
    @ApiOperation("添加分类")
    @PostMapping
    public Result<Integer> add(@RequestBody Category category){
        int add = categoryService.add(category);
        if (add > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"添加成功", add);
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败", add);
    }

    /***
     * 根据ID查询Category数据
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询数据")
    @GetMapping("{id}")
    public Result<Category> findById(@PathVariable Long id){
        //根据ID查询
        Category category = categoryService.findById(id);
        if (ObjectUtil.isNotEmpty(category)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功",category);
        }
        return new Result<>(false, StatusCode.ERROR,"查询失败",category);
    }

    /***
     * 查询Category全部数据
     * @return
     */
    @ApiOperation("查询全部数据")
    @GetMapping
    public Result<List<Category>> findAll(){
        List<Category> list = categoryService.findAll();
        if (CollUtil.isNotEmpty(list)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功",list) ;
        }
        return new Result<>(false, StatusCode.ERROR,"查询失败",list) ;
    }

    /**
     * 根据父ID查询
     */
    @ApiOperation("根据父ID查询")
    @RequestMapping(value ="list/{pid}")
    public Result<Category> findByPrantId(@PathVariable(value = "pid")Long pid){
        //根据父节点ID查询
        List<Category> list = categoryService.findByParentId(pid);
        if (CollUtil.isNotEmpty(list)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功",list) ;
        }
        return new Result<>(false, StatusCode.ERROR,"查询失败",list) ;
    }
}
