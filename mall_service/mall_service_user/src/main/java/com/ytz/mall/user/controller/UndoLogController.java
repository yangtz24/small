package com.ytz.mall.user.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.user.pojo.UndoLog;
import com.ytz.mall.user.service.UndoLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author yangt
 */
@Api(tags = "UndoLogController", description = "操作记录Controller")
@RestController
@RequestMapping("undoLog")
@CrossOrigin
public class UndoLogController {

    @Autowired
    private UndoLogService undoLogService;

    /***
     * UndoLog分页条件搜索实现
     * @param undoLog
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<UndoLog>> findPage(@RequestBody(required = false) UndoLog undoLog, @PathVariable int page, @PathVariable int size){
        //调用UndoLogService实现分页条件查询UndoLog
        PageInfo<UndoLog> pageInfo = undoLogService.findPage(undoLog, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * UndoLog分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<UndoLog>> findPage(@PathVariable int page, @PathVariable int size){
        //调用UndoLogService实现分页查询UndoLog
        PageInfo<UndoLog> pageInfo = undoLogService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param undoLog
     * @return
     */
    @ApiOperation("条件搜索")
    @PostMapping(value = "list" )
    public Result<List<UndoLog>> findList(@RequestBody(required = false) UndoLog undoLog){
        //调用UndoLogService实现条件查询UndoLog
        List<UndoLog> list = undoLogService.findList(undoLog);
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
        //调用UndoLogService实现根据主键删除
        int delete = undoLogService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改UndoLog数据
     * @param undoLog
     * @param id
     * @return
     */
    @ApiOperation("修改")
    @PutMapping(value="/{id}")
    public Result<Integer> update(@RequestBody  UndoLog undoLog,@PathVariable Long id){
        //设置主键值
        undoLog.setId(id);
        //调用UndoLogService实现修改UndoLog
        int update = undoLogService.update(undoLog);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增UndoLog数据
     * @param undoLog
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody UndoLog undoLog){
        //调用UndoLogService实现添加UndoLog
        int add = undoLogService.add(undoLog);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询UndoLog数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<UndoLog> findById(@PathVariable Long id){
        //调用UndoLogService实现根据主键查询UndoLog
        UndoLog undoLog = undoLogService.findById(id);
        if (ObjectUtil.isNotEmpty(undoLog)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", undoLog);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", undoLog);
    }

    /***
     * 查询UndoLog全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<List<UndoLog>> findAll(){
        //调用UndoLogService实现查询所有UndoLog
        List<UndoLog> all = undoLogService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }
}
