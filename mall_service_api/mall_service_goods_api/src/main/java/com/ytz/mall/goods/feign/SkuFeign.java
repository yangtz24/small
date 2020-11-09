package com.ytz.mall.goods.feign;

import com.ytz.mall.common.Result;
import com.ytz.mall.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author yangt
 */
@FeignClient(value="GOODS-SERVICE")
@RequestMapping("sku")
public interface SkuFeign {
    /**
     * 查询符合条件的状态的SKU的列表
     * @param status
     * @return
     */
    @GetMapping("status/{status}")
    Result<List<Sku>> findByStatus(@PathVariable(value = "status") String status);


    /**
     * 根据条件搜索的SKU的列表
     * @param sku
     * @return
     */
    @PostMapping(value = "list" )
    Result<List<Sku>> findList(@RequestBody(required = false) Sku sku);

    /**
     * 查询单个
     * @param id
     * @return
     */
    @GetMapping("{id}")
    Result<Sku> findById(@PathVariable(value = "id") Long id);
}
