package com.ytz.mall.goods.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Goods;
import com.ytz.mall.goods.pojo.Spu;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.goods.service.SpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SpuController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/18
 * @Version: V1.0
 */
@Api(tags = "SpuController", description = "SpuController")
@RestController
@RequestMapping("spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    /***
     * Spu分页条件搜索实现
     * @param spu
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}")
    public Result<PageInfo<Spu>> findPage(@RequestBody(required = false) Spu spu, @PathVariable int page, @PathVariable int size){
        //调用SpuService实现分页条件查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(spu, page, size);
        if (ObjectUtil.isNotNull(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功", pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", pageInfo);
    }

    /***
     * Spu分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Spu>> findPage(@PathVariable int page, @PathVariable int size){
        //调用SpuService实现分页查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(page, size);
        if (ObjectUtil.isNotNull(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param spu
     * @return
     */
    @ApiOperation("多条件搜索")
    @PostMapping(value = "list" )
    public Result<List<Spu>> findList(@RequestBody(required = false) Spu spu){
        //调用SpuService实现条件查询Spu
        List<Spu> list = spuService.findList(spu);
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
        //调用SpuService实现根据主键删除
        int delete = spuService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功", delete);
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败", delete);
    }

    /***
     * 修改Spu数据
     * @param spu
     * @param id
     * @return
     */
    @ApiOperation("修改数据")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody  Spu spu,@PathVariable Long id){
        //设置主键值
        spu.setId(id);
        //调用SpuService实现修改Spu
        int update = spuService.update(spu);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功", update);
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败", update);
    }

    /***
     * 新增Spu数据
     * @param spu
     * @return
     */
    @ApiOperation("添加数据")
    @PostMapping
    public Result<Integer> add(@RequestBody Spu spu){
        //调用SpuService实现添加Spu
        int add = spuService.add(spu);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功", add);
        }
        return new Result<>(false, StatusCode.ERROR,"添加失败", add);
    }

    /***
     * 根据ID查询Spu数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个数据")
    @GetMapping("{id}")
    public Result<Spu> findById(@PathVariable Long id){
        //调用SpuService实现根据主键查询Spu
        Spu spu = spuService.findById(id);
        if (ObjectUtil.isNotNull(spu)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",spu);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",spu);
    }

    /***
     * 查询Spu全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<List<Spu>> findAll() {
        //调用SpuService实现查询所有Spu
        List<Spu> all = spuService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", all);
        }
        return new Result<>(false, StatusCode.ERROR, "查询失败", all);

    }

    /**
     * Goods(SPU+SKU)增加方法详情
     */
    @ApiOperation("(SPU+SKU)添加")
    @PostMapping("save")
    public Result<Integer> save(@RequestBody Goods goods){
        int save = spuService.save(goods);
        if (save > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"保存商品成功",save);
        }
        return new Result<>(false,StatusCode.ERROR,"保存商品失败",save);
    }

    /**
     * 根据点击到的商品(SPU)的ID 获取到GOODS数据返回给页面展示
     * @param id
     * @return
     */
    @ApiOperation("根据商品的(SPU)的ID获取商品数据")
    @GetMapping("goods/{id}")
    public Result<Goods> findGoodsById(@PathVariable(value="id") Long id){
        Goods goods = spuService.findGoodsById(id);
        if (ObjectUtil.isNotNull(goods)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",goods);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",goods);
    }

    /**
     * 商品上架
     * @param id
     * @return
     */
    @ApiOperation("上架")
    @PutMapping("put/{id}")
    public Result<Integer> put(@PathVariable Long id){
        int put = spuService.put(id);
        if (put > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"上架成功", put);
        }
        return new Result<>(false,StatusCode.ERROR,"上架失败", put);
    }

    /**
     *  批量上架
     * @param ids
     * @return
     */
    @ApiOperation("批量上架")
    @PutMapping("put/batch")
    public Result<Integer> putMany(@RequestBody Long[] ids){
        int count = spuService.batchPut(ids);
        if (count > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"批量上架成功", count);
        }
        return new Result<>(false,StatusCode.ERROR,"批量上架失败", count);
    }

    /**
     * //审核商品 自动上架
     * @param id  spu的ID
     * @return
     */
    @ApiOperation("审核")
    @PutMapping("audit/{id}")
    public Result<Integer> auditSpu(@PathVariable(name="id")Long id){
        int audit = spuService.auditSpu(id);
        if (audit > 0) {
            return new Result(true,StatusCode.SUCCESS,"审核通过", audit);
        }
        return new Result(false,StatusCode.ERROR,"审核失败", audit);
    }

    /**
     *
     * @param id
     * @return
     */
    @PutMapping("/pull/{id}")
    public Result pullSpu(@PathVariable(name="id")Long id){
        spuService.pullSpu(id);
        return new Result(true, StatusCode.SUCCESS,"下架成功");
    }

    @DeleteMapping("/logic/delete/{id}")
    public Result logicDeleteSpu(@PathVariable(name="id")Long id){
        spuService.logicDeleteSpu(id);
        return new Result(true,StatusCode.SUCCESS,"逻辑删除成功");
    }

    @PutMapping("/restore/{id}")
    public Result restore(@PathVariable(name="id")Long id){
        spuService.restoreSpu(id);
        return new Result(true,StatusCode.SUCCESS,"还原成功");
    }
}
