package com.ytz.mall.goods.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.ytz.mall.goods.pojo.Brand;
import com.ytz.mall.common.Result;
import com.ytz.mall.common.StatusCode;
import com.ytz.mall.goods.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: BrandControlller
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/15
 * @Version: V1.0
 */
@Api(tags = "BrandControlller", description = "品牌Controlller")
@RestController
@RequestMapping("brand")
@CrossOrigin
public class BrandControlller {

    @Autowired
    private BrandService brandService;

    @ApiOperation("添加品牌")
    @PostMapping("add")
    public Result<Integer> add(@RequestBody Brand brand) {
        int result = brandService.addBrand(brand);
        if (result > 0) {
            return new Result<>(true, StatusCode.SUCCESS, "添加成功", result);
        }
        return new Result<>(true, StatusCode.ERROR, "添加失败", result);

    }

    @ApiOperation("修改品牌")
    @PutMapping("{id}")
    public Result<Integer> modify(@PathVariable Long id, @RequestBody Brand brand) {
        brand.setId(id);
        int result = brandService.updateBrand(id, brand);
        if (result > 0) {
            return new Result<>(true, StatusCode.SUCCESS, "修改成功", result);
        }
        return new Result<>(true, StatusCode.ERROR, "修改失败", result);

    }

    @ApiOperation("删除品牌")
    @DeleteMapping("delete/{id}")
    public Result<Integer> remove(@PathVariable Long id) {
        int result = brandService.removeBrand(id);
        if (result > 0) {
            return new Result<>(true, StatusCode.SUCCESS, "删除成功", result);
        }
        return new Result<>(true, StatusCode.ERROR, "删除失败", result);

    }

    @ApiOperation("查询所有")
    @GetMapping("all")
    public Result<List<Brand>> all() {
        List<Brand> brands = brandService.findAll();
        if (CollUtil.isNotEmpty(brands)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询数据成功", brands);
        }
        return new Result<>(true, StatusCode.ERROR, "查询数据失败", brands);

    }

    @ApiOperation("查询一个")
    @GetMapping("one/{id}")
    public Result<Brand> one(@PathVariable Long id) {
        Brand brand = brandService.findOne(id);
        if (ObjectUtil.isNotEmpty(brand)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询数据成功", brand);
        }
        return new Result<>(true, StatusCode.ERROR, "查询数据失败", brand);

    }

    @ApiOperation("条件查询")
    @PostMapping("search")
    public Result<List<Brand>> conditionList(@RequestBody Brand brand) {
        List<Brand> brandList = brandService.findListByCondition(brand);
        if (CollUtil.isNotEmpty(brandList)) {
            return new Result<>(true, StatusCode.SUCCESS, "条件查询数据成功", brandList);
        }
        return new Result<>(true, StatusCode.ERROR, "条件查询数据失败", brandList);

    }

    @ApiOperation("分页查询")
    @GetMapping("search/{page}/{size}")
    public Result<PageInfo<Brand>> pageList(@PathVariable("page")Integer currentPage, @PathVariable("size") Integer pageSize) {
        PageInfo<Brand> pageInfo = brandService.pageList(currentPage, pageSize);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true, StatusCode.SUCCESS, "分页查询数据成功", pageInfo);
        }
        return new Result<>(true, StatusCode.ERROR, "分页查询数据失败", pageInfo);

    }

    @ApiOperation("分页查询")
    @PostMapping("search/{page}/{size}")
    public Result<PageInfo<Brand>> conditionPageList(@PathVariable("page")Integer currentPage, @PathVariable("size") Integer pageSize, @RequestBody Brand brand) {
        PageInfo<Brand> pageInfo = brandService.pageListByCondition(currentPage, pageSize, brand);
        if (ObjectUtil.isNotEmpty(pageInfo)) {
            return new Result<>(true, StatusCode.SUCCESS, "分页条件查询数据成功", pageInfo);
        }
        return new Result<>(true, StatusCode.ERROR, "分页条件查询数据失败", pageInfo);

    }

    /**
     * 查询分类
     * @return
     */
    @ApiOperation("查询分类")
    @GetMapping("category/{id}")
    public Result<List<Brand>> findBrandByCategory(@PathVariable(name="id") Long id){
        List<Brand> brandList = brandService.findByCategory(id);
        if (CollUtil.isNotEmpty(brandList)) {
            return new Result<>(true, StatusCode.SUCCESS, "查询分类数据成功", brandList);
        }
        return new Result<>(true, StatusCode.ERROR, "查询分类数据失败", brandList);

    }
}
