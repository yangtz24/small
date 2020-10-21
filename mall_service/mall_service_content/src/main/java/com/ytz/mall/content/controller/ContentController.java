package com.ytz.mall.content.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.content.pojo.Content;
import com.ytz.mall.content.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: ContentController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/20
 * @Version: V1.0
 */
@Api(tags = "ContentCategoryController", description = "广告Controller")
@RestController
@RequestMapping("content")
@CrossOrigin
public class ContentController {

    @Autowired
    private ContentService contentService;

    /***
     * Content分页条件搜索实现
     * @param content
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Content>> findPage(@RequestBody(required = false) Content content, @PathVariable int page, @PathVariable int size){
        //调用ContentService实现分页条件查询Content
        PageInfo<Content> pageInfo = contentService.findPage(content, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Content分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Content>> findPage(@PathVariable int page, @PathVariable int size){
        //调用ContentService实现分页查询Content
        PageInfo<Content> pageInfo = contentService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param content
     * @return
     */
    @ApiOperation("多条件搜索")
    @PostMapping(value = "list" )
    public Result<List<Content>> findList(@RequestBody(required = false) Content content){
        //调用ContentService实现条件查询Content
        List<Content> list = contentService.findList(content);
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
        //调用ContentService实现根据主键删除
        int delete = contentService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改Content数据
     * @param content
     * @param id
     * @return
     */
    @ApiOperation("修改")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Content content,@PathVariable Long id){
        //设置主键值
        content.setId(id);
        //调用ContentService实现修改Content
        int update = contentService.update(content);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增Content数据
     * @param content
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody Content content){
        //调用ContentService实现添加Content
        int add = contentService.add(content);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询Content数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<Content> findById(@PathVariable Long id){
        //调用ContentService实现根据主键查询Content
        Content content = contentService.findById(id);
        if (ObjectUtil.isNotEmpty(content)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", content);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", content);
    }

    /***
     * 查询Content全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<List<Content>> findAll(){
        //调用ContentService实现查询所有Content
        List<Content> all = contentService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }


    /**
     * 根据分类的ID 获取该分类下的所有的广告的列表
     * @param id
     * @return
     */
    @ApiOperation("根据分类的ID查询")
    @GetMapping(value = "list/category/{id}")
    public Result<List<Content>> findByCategory(@PathVariable(name="id") Long id){
        List<Content> contents = contentService.findByCategory(id);
        if (CollUtil.isNotEmpty(contents)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", contents) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", contents);
    }
}
