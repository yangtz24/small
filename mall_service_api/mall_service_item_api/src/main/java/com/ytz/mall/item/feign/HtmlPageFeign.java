package com.ytz.mall.item.feign;

import com.ytz.mall.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: HtmlPageFeign
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/10/29
 * @Version: V1.0
 */
@FeignClient(name = "ITEM-SERVICE")
@RequestMapping("page")
public interface HtmlPageFeign {

    /***
     * 根据SpuID生成静态页
     * @param id
     * @return
     */
    @RequestMapping("/createHtml/{id}")
    Result<?> createHtml(@PathVariable(name="id") Long id);
}
