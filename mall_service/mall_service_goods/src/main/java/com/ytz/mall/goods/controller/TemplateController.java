package com.ytz.mall.goods.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Template;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.goods.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: TemplateController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@Api(tags = "TemplateController", description = "规格参数模板Controller")
@RestController
@RequestMapping("template")
@CrossOrigin
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /***
     * Template分页条件搜索实现
     * @param template
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Template>> findPage(@RequestBody(required = false)  Template template, @PathVariable  int page, @PathVariable  int size){
        //执行搜索
        PageInfo<Template> pageInfo = templateService.findPage(template, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Template分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Template>> findPage(@PathVariable  int page, @PathVariable  int size){
        //分页查询
        PageInfo<Template> pageInfo = templateService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param template
     * @return
     */
    @ApiOperation("列表查询")
    @PostMapping(value = "list" )
    public Result<List<Template>> findList(@RequestBody(required = false)  Template template){
        List<Template> list = templateService.findList(template);
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
        int delete = templateService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功", delete);
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败",delete);
    }

    /***
     * 修改Template数据
     * @param template
     * @param id
     * @return
     */
    @ApiOperation("修改数据")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody  Template template,@PathVariable Long id){
        //设置主键值
        template.setId(id);
        //修改数据
        int update = templateService.update(template);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功", update);
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败",update);

    }

    /***
     * 新增Template数据
     * @param template
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody Template template){
        int add = templateService.add(template);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功", add);
        }
        return new Result<>(false, StatusCode.ERROR,"添加失败", add);
    }

    /***
     * 根据ID查询Template数据
     * @param id
     * @return
     */
    @ApiOperation("查询一个")
    @GetMapping("{id}")
    public Result<Template> findById(@PathVariable Long id){
        //根据ID查询
        Template template = templateService.findById(id);
        if (ObjectUtil.isNotNull(template)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", template);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",template);
    }

    /***
     * 查询Template全部数据
     * @return
     */
    @ApiOperation("全部数据")
    @GetMapping
    public Result<Template> findAll(){
        List<Template> all = templateService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", all);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",all);
    }

    /***
     * 根据分类查询模板数据
     * @param id:分类ID
     */
    @ApiOperation("根据分类查询数据")
    @GetMapping(value = "category/{id}")
    public Result<Template> findByCategoryId(@PathVariable(value = "id")Long id){
        //调用Service查询
        Template template = templateService.findByCategoryId(id);
        if (ObjectUtil.isNotNull(template)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", template);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",template);
    }
}
