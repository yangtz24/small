package com.ytz.mall.goods.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Para;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.goods.service.ParaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: ParaController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@Api(tags = "ParaController", description = "商品参数Controller")
@RestController
@RequestMapping("para")
@CrossOrigin
public class ParaController {

    @Autowired
    private ParaService paraService;

    /***
     * Para分页条件搜索实现
     * @param para
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Para>> findPage(@RequestBody(required = false) Para para, @PathVariable int page, @PathVariable int size){
        //执行搜索
        PageInfo<Para> pageInfo = paraService.findPage(para, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Para分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Para>> findPage(@PathVariable int page, @PathVariable int size){
        //分页查询
        PageInfo<Para> pageInfo = paraService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param para
     * @return
     */
    @ApiOperation("列表查询")
    @PostMapping(value = "list" )
    public Result<List<Para>> findList(@RequestBody(required = false) Para para){
        List<Para> list = paraService.findList(para);
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
        int delete = paraService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功", delete);
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败", delete);
    }

    /***
     * 修改Para数据
     * @param para
     * @param id
     * @return
     */
    @ApiOperation("修改数据")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Para para,@PathVariable Long id){
        //设置主键值
        para.setId(id);
        //修改数据
        int update = paraService.update(para);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功", update);
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败", update);
    }

    /***
     * 新增Para数据
     * @param para
     * @return
     */
    @ApiOperation("添加数据")
    @PostMapping
    public Result<Integer> add(@RequestBody Para para){
        int add = paraService.add(para);
        if (add > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"添加成功", add);
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败", add);
    }

    /***
     * 根据ID查询Para数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个数据")
    @GetMapping("{id}")
    public Result<Para> findById(@PathVariable Long id){
        //根据ID查询
        Para para = paraService.findById(id);
        if (ObjectUtil.isNotEmpty(para)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功",para);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",para);
    }

    /***
     * 查询Para全部数据
     * @return
     */
    @ApiOperation("查询全部数据")
    @GetMapping
    public Result<Para> findAll(){
        List<Para> all = paraService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",all);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",all);
    }

    /**
     * 根据分类ID查询参数列表
     * @param id
     * @return
     */
    @ApiOperation("根据分类ID查询")
    @GetMapping(value = "category/{id}")
    public Result<List<Para>> getByCategoryId(@PathVariable(value = "id")Long id){
        //根据分类ID查询对应的参数信息
        List<Para> paras = paraService.findByCategoryId(id);
        if (CollUtil.isNotEmpty(paras)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",paras);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",paras);
    }
}
