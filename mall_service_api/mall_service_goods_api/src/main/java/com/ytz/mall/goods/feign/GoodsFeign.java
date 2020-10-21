package com.ytz.mall.goods.feign;

import com.ytz.mall.goods.pojo.Sku;
import com.ytz.mall.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: GoodsFeign
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/21
 * @Version: V1.0
 */
@FeignClient(name = "GOODS-SERVICE")
@RequestMapping("/sku")
public interface GoodsFeign {

    /**
     * 查询所有
     * @return
     */
    @GetMapping
    Result<List<Sku>> findAll();

    /**
     * 查询部分
     * @return
     */
    @GetMapping("limit")
    Result<List<Sku>> findLimitAll();
}
