package com.ytz.mall.goods.feign;

import com.ytz.mall.common.Result;
import com.ytz.mall.goods.pojo.Spu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author yangt
 */
@FeignClient(value="GOODS-SERVICE")
@RequestMapping("spu")
public interface SpuFeign {

    /**
     * 查询详情
     * @param id
     * @return
     */
    @GetMapping("{id}")
    Result<Spu> findById(@PathVariable(value = "id") Long id);
}
