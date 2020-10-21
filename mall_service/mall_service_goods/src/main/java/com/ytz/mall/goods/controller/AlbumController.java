package com.ytz.mall.goods.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Album;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.goods.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: AlbumController
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/16
 * @Version: V1.0
 */
@Api(tags = "AlbumController", description = "相册Controller")
@RestController
@RequestMapping("album")
@CrossOrigin
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    /***
     * Album分页条件搜索实现
     * @param album
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索实现")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Album>> findPage(@RequestBody(required = false) Album album, @PathVariable int page, @PathVariable int size){
        //执行搜索
        PageInfo<Album> pageInfo = albumService.findPage(album, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Album分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索实现")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size){
        //分页查询
        PageInfo<Album> pageInfo = albumService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param album
     * @return
     */
    @ApiOperation("多条件搜索数据")
    @PostMapping(value = "list" )
    public Result<List<Album>> findList(@RequestBody(required = false) Album album){
        List<Album> list = albumService.findList(album);
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
    @ApiOperation("根据ID删除数据")
    @DeleteMapping(value = "{id}" )
    public Result<Integer> delete(@PathVariable Long id){
        int delete = albumService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改Album数据
     * @param album
     * @param id
     * @return
     */
    @ApiOperation("修改数据")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Album album,@PathVariable Long id){
        //设置主键值
        album.setId(id);
        //修改数据
        int update = albumService.update(album);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增Album数据
     * @param album
     * @return
     */
    @ApiOperation("添加数据")
    @PostMapping
    public Result<Integer> add(@RequestBody Album album){
        int add = albumService.add(album);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询Album数据
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询数据")
    @GetMapping("{id}")
    public Result<Album> findById(@PathVariable Long id){
        //根据ID查询
        Album album = albumService.findById(id);
        if (ObjectUtil.isNotEmpty(album)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", album);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", album);
    }

    /***
     * 查询Album全部数据
     * @return
     */
    @ApiOperation("查询全部数据")
    @GetMapping
    public Result<Album> findAll(){
        List<Album> all = albumService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }
}
