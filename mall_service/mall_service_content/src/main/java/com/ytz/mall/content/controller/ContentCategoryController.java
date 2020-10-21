package com.ytz.mall.content.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.content.pojo.ContentCategory;
import com.ytz.mall.content.service.ContentCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: ContentCategoryController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/20
 * @Version: V1.0
 */
@Api(tags = "ContentCategoryController", description = "分类Controller")
@RestController
@RequestMapping("category")
@CrossOrigin
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /***
     * ContentCategory分页条件搜索实现
     * @param contentCategory
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<ContentCategory>> findPage(@RequestBody(required = false) ContentCategory contentCategory, @PathVariable int page, @PathVariable int size){
        //调用ContentCategoryService实现分页条件查询ContentCategory
        PageInfo<ContentCategory> pageInfo = contentCategoryService.findPage(contentCategory, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * ContentCategory分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<ContentCategory>> findPage(@PathVariable int page, @PathVariable int size){
        //调用ContentCategoryService实现分页查询ContentCategory
        PageInfo<ContentCategory> pageInfo = contentCategoryService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param contentCategory
     * @return
     */
    @ApiOperation("多条件搜索")
    @PostMapping(value = "list" )
    public Result<List<ContentCategory>> findList(@RequestBody(required = false) ContentCategory contentCategory){
        //调用ContentCategoryService实现条件查询ContentCategory
        List<ContentCategory> list = contentCategoryService.findList(contentCategory);
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
        //调用ContentCategoryService实现根据主键删除
        int delete = contentCategoryService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改ContentCategory数据
     * @param contentCategory
     * @param id
     * @return
     */
    @ApiOperation("修改")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody ContentCategory contentCategory, @PathVariable Long id){
        //设置主键值
        contentCategory.setId(id);
        //调用ContentCategoryService实现修改ContentCategory
        int update = contentCategoryService.update(contentCategory);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增ContentCategory数据
     * @param contentCategory
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody ContentCategory contentCategory){
        //调用ContentCategoryService实现添加ContentCategory
        int add = contentCategoryService.add(contentCategory);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询ContentCategory数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<ContentCategory> findById(@PathVariable Long id){
        //调用ContentCategoryService实现根据主键查询ContentCategory
        ContentCategory contentCategory = contentCategoryService.findById(id);
        if (ObjectUtil.isNotEmpty(contentCategory)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", contentCategory);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", contentCategory);
    }

    /***
     * 查询ContentCategory全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<List<ContentCategory>> findAll(){
        //调用ContentCategoryService实现查询所有ContentCategory
        List<ContentCategory> all = contentCategoryService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }
}
