package com.ytz.mall.goods.feign;

import com.ytz.mall.common.Result;
import com.ytz.mall.goods.pojo.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author yangt
 */
@FeignClient(value="GOODS-SERVICE")
@RequestMapping("category")
public interface CategoryFeign {
    /**
     * 查询单个
     * @param id
     * @return
     */
    @GetMapping("{id}")
    Result<Category> findById(@PathVariable(name = "id") Long id);
}
