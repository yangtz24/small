package com.ytz.mall.user.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.common.TokenDecode;
import com.ytz.mall.user.pojo.Address;
import com.ytz.mall.user.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * @author yangt
 */
@Api(tags = "AddressController", description = "地址Controller")
@RestController
@RequestMapping("address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Resource
    private TokenDecode tokenDecode;

    /***
     * Address分页条件搜索实现
     * @param address
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("分页条件搜索")
    @PostMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Address>> findPage(@RequestBody(required = false) Address address, @PathVariable int page, @PathVariable int size){
        //调用AddressService实现分页条件查询Address
        PageInfo<Address> pageInfo = addressService.findPage(address, page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * Address分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping(value = "search/{page}/{size}" )
    public Result<PageInfo<Address>> findPage(@PathVariable int page, @PathVariable int size){
        //调用AddressService实现分页查询Address
        PageInfo<Address> pageInfo = addressService.findPage(page, size);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true,StatusCode.SUCCESS,"查询成功",pageInfo);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param address
     * @return
     */
    @ApiOperation("条件搜索列表")
    @PostMapping(value = "list" )
    public Result<List<Address>> findList(@RequestBody(required = false)  Address address){
        //调用AddressService实现条件查询Address
        List<Address> list = addressService.findList(address);
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
        //调用AddressService实现根据主键删除
        int delete = addressService.delete(id);
        if (delete > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"删除成功");
        }
        return new Result<>(false,StatusCode.ERROR,"删除失败");
    }

    /***
     * 修改Address数据
     * @param address
     * @param id
     * @return
     */
    @ApiOperation("修改")
    @PutMapping(value="{id}")
    public Result<Integer> update(@RequestBody Address address,@PathVariable Long id){
        //设置主键值
        address.setId(id);
        //调用AddressService实现修改Address
        int update = addressService.update(address);
        if (update > 0) {
            return new Result<>(true,StatusCode.SUCCESS,"修改成功");
        }
        return new Result<>(false,StatusCode.ERROR,"修改失败");
    }

    /***
     * 新增Address数据
     * @param address
     * @return
     */
    @ApiOperation("添加")
    @PostMapping
    public Result<Integer> add(@RequestBody Address address){
        //调用AddressService实现添加Address
        int add = addressService.add(address);
        if (add > 0) {
            return new Result<>(true, StatusCode.SUCCESS,"添加成功");
        }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /***
     * 根据ID查询Address数据
     * @param id
     * @return
     */
    @ApiOperation("查询单个")
    @GetMapping("{id}")
    public Result<Address> findById(@PathVariable Long id){
        //调用AddressService实现根据主键查询Address
        Address address = addressService.findById(id);
        if (ObjectUtil.isNotEmpty(address)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询成功", address);
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", address);
    }

    /***
     * 查询Address全部数据
     * @return
     */
    @ApiOperation("查询全部")
    @GetMapping
    public Result<List<Address>> findAll(){
        //调用AddressService实现查询所有Address
        List<Address> all = addressService.findAll();
        if (CollUtil.isNotEmpty(all)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", all) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", all);
    }


    @ApiOperation("根据用户名查询地址")
    @GetMapping("username")
    public Result<List<Address>> findByUsername(){
        String username = tokenDecode.getUserInfo().get("username");
        List<Address> addressList = addressService.findByUserName(username);
        if (CollUtil.isNotEmpty(addressList)) {
            return new Result<>(true, StatusCode.SUCCESS,"查询成功", addressList) ;
        }
        return new Result<>(false,StatusCode.ERROR,"查询失败", addressList);
    }
}
